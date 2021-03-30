package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Permit;
import pl.odum.workflowodum.repository.DocRepository;
import pl.odum.workflowodum.word.WordMerge;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DocServiceImpl implements DocService {
    private final static String USERS_BASE_PATH = "/home/maciej/odum-docs/clients";
    private final static String FILE_NOT_FOUND_EXC = "File not found";
    private final static String RESPONSE_CONTENT_TYPE = "application/octet-stream";
    private final static String HEADER_KEY = "Content-Disposition";
    private final static String HEADER_VALUE = "attachment; filename=";
    private final DocRepository docRepository;
    private final MeetingService meetingService;

    @Override
    public List<Doc> getFiles() {
        return docRepository.findAll();
    }

    @Override
    public Doc findById(Long id) {
        return docRepository.findById(id).orElseThrow(() -> new IllegalStateException("File does not exists"));
    }

    @Override
    public File findFileByClientAndFileName(Client client, String name) {
        File file = new File(USERS_BASE_PATH + "/" + client.getName() + "/" + name);
        if (file.exists()) {
            return file;
        }

        throw new IllegalStateException(FILE_NOT_FOUND_EXC);
    }

    @Override
    public File findFileByDoc(Doc doc) {
        File file = new File(doc.getSourcePath() + "/" + doc.getDocName());
        if (file.exists()) {
            return file;
        }

        throw new IllegalStateException(FILE_NOT_FOUND_EXC);
    }

    @Override
    @Transactional
    public void saveFile(MultipartFile file,Client client,Permit permit) throws IOException {
        Doc doc = new Doc();
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());
        doc.setSourcePath(client.getHomePath()+"/"+permit.getType());


        file.transferTo(new File(doc.getSourcePath() + "/" + doc.getDocName()));
        docRepository.save(doc);
    }

    @Override
    public void saveFilesFromMultiPart(List<MultipartFile> files,Client client,Permit permit) {
        files.forEach(file -> {
            try {
                saveFile(file,client,permit);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    @Async
    public void download(Doc doc, HttpServletResponse response) {
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setHeader(HEADER_KEY, HEADER_VALUE + doc.getDocName());
        File file = findFileByDoc(doc);
        try (ServletOutputStream os = response.getOutputStream()) {
            os.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareDocToRemoving(Long id) {
        Doc doc = docRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        doc.setToRemove(true);
        doc.setDateOfRemoving(LocalDate.now().plusDays(7));
        docRepository.save(doc);
    }


    @Override
    public void removeDocs() {
        List<Doc> docsToRemove = docRepository.findAllByDateOfRemovingBeforeAndDateOfRemovingIsNotNull(LocalDate.now());
        docsToRemove.forEach(doc -> docRepository.deleteById(doc.getId()));
    }


    //Download merged documents
    @Override
    public void downloadMergedClientsDocx(Client client, HttpServletResponse response) {
        Set<Doc> docs = findAllByClient(client);
        mergeDocs(docs, response);
    }

    @Override
    public void downloadAll(HttpServletResponse response) {
        Set<Doc> docs = new HashSet<>(docRepository.findAll());
        mergeDocs(docs, response);
    }

    private Set<Doc> findAllByClient(Client client) {
        List<Meeting> meetings = meetingService.findAllByClient(client);
        Set<Doc> docs = new HashSet<>();
        meetings.forEach(m -> {
            docs.addAll(m.getDoc().stream().filter(Objects::nonNull).collect(Collectors.toSet()));
        });
        return docs;
    }

    private void mergeDocs(Set<Doc> docs, HttpServletResponse response) {
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setHeader(HEADER_KEY, HEADER_VALUE + "merged.docx");
        List<InputStream> inputStreams = docs.stream().map(d -> {
            try {
                return new FileInputStream(d.getSourcePath() + "/" + d.getDocName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        })
                .filter(Objects::nonNull)
                .collect(toList());

        try (OutputStream os = response.getOutputStream()) {
            WordMerge wordMerge = new WordMerge(os);
            for (InputStream inputStream : inputStreams) {
                wordMerge.add(inputStream);
            }
            wordMerge.doMerge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
