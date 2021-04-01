package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
import pl.odum.workflowodum.utils.DirectoryCreator;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DocServiceImpl implements DocService {

    private final static String RESPONSE_CONTENT_TYPE = "application/octet-stream";
    private final static String HEADER_KEY = "Content-Disposition";
    private final static String HEADER_VALUE = "attachment; filename=";
    private final DocRepository docRepository;
    private final MeetingService meetingService;
    private final DirectoryCreator directoryCreator;

    @Override
    public List<Doc> getFiles() {
        return docRepository.findAll();
    }

    @Override
    public Doc findById(Long id) {
        return docRepository.findById(id).orElseThrow(() -> new IllegalStateException("File does not exists"));
    }

    @Override
    @Transactional
    public void saveFile(MultipartFile file,Client client,Permit permit,Long userId) throws IOException {
        Doc doc = new Doc();
        doc.setUuid(UUID.randomUUID().toString());
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());
        doc.setClient(client);
        doc.setPermit(permit);
        doc.setToRemove(false);
        doc.setUserAddingId(userId);
        doc.setSourcePath(client.getHomePath()+"/"+permit.getType());
        directoryCreator.createDirectoryPermitForClient(doc.getSourcePath());
        File destination=doc.getFile();
        file.transferTo(destination);
        docRepository.save(doc);
    }

    @Transactional
    public void addNoteToMeeting(MultipartFile file, Meeting meeting) throws IOException {
        Doc doc = new Doc();
        doc.setUuid(UUID.randomUUID().toString());
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());
        doc.setSourcePath(meeting.getClient().getHomePath() + "/meetings");

        Files.createDirectories(Paths.get(doc.getSourcePath()));

        file.transferTo(doc.getFile());

        List<Doc> docs = meeting.getDoc();
        docs.add(doc);

        docRepository.save(doc);
        meetingService.save(meeting);
    }

    @Override
    @Transactional
    public void addNotesToMeeting(List<MultipartFile> files, Meeting meeting) {
        files.forEach(file -> {
            try {
                addNoteToMeeting(file, meeting);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void saveFilesFromMultiPart(List<MultipartFile> files,Client client,Permit permit,Long userId) {
        files.forEach(file -> {
            try {
                saveFile(file,client,permit,userId);
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
        File file =doc.getFile();
        try (ServletOutputStream os = response.getOutputStream()) {
            os.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SneakyThrows
    public void prepareDocToRemoving(String uuid) {
        Doc doc = docRepository.findByUuid(uuid);
        doc.setToRemove(true);
        doc.setDateOfRemoving(LocalDate.now().plusDays(7));
        docRepository.save(doc);
    }

    @Transactional
    @Override
    public void removeDocs() {
        List<Doc> docsToRemove = docRepository.findAllByDateOfRemovingBeforeAndDateOfRemovingIsNotNull(LocalDate.now());
        docsToRemove.forEach(doc -> {
            try {
                Files.delete(Path.of(doc.getSourcePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            docRepository.deleteByUuid(doc.getUuid());
        });
    }


    //Download merged documents
    @SneakyThrows
    @Override
    public void downloadMergedClientsDocx(Client client, HttpServletResponse response) throws IOException {
        Set<Doc> docs = findAllByClient(client);
        Set<Doc> collect = docs.stream().filter(doc -> doc.getSourcePath().endsWith("/meetings")).collect(Collectors.toSet());
        mergeDocs(collect, response);
    }

    private Set<Doc> findAllByClient(Client client) {
        List<Meeting> meetings = meetingService.findAllByClient(client);
        Set<Doc> docs = new HashSet<>();
        meetings.forEach(m -> docs.addAll(m.getDoc().stream().filter(Objects::nonNull).collect(Collectors.toSet())));
        return docs;
    }

    private void mergeDocs(Set<Doc> docs, HttpServletResponse response) throws Exception {
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setHeader(HEADER_KEY, HEADER_VALUE + "merged.docx");
        WordMerge wordMerge=new WordMerge();
        wordMerge.WordMerge(docs,response.getOutputStream());
    }

}
