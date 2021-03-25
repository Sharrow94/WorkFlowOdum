package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;
import pl.odum.workflowodum.repository.DocRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DocServiceImpl implements DocService {
    private final static String USERS_BASE_PATH = "/home/mcs/IdeaProjects/odum-docs/users";
    private final DocRepository docRepository;

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

        throw new IllegalStateException("File not found");
    }

    @Override
    public File findFileByDoc(Doc doc) {
        File file = new File(doc.getSourcePath() + "/" + doc.getDocName());
        if (file.exists()) {
            return file;
        }

        throw new IllegalStateException("File not found");
    }

    @Override
    public Doc findByDocNameAndClientAndPermit(String docName, Client client, Permit permit) {
        return docRepository.findByDocNameAndClientAndPermit(docName, client, permit);
    }

    @Override
    @Transactional
    public void saveFile(MultipartFile file) throws IOException {
        Doc doc = new Doc();
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());
        doc.setSourcePath(USERS_BASE_PATH);


        file.transferTo(new File(doc.getSourcePath() + "/" + doc.getDocName()));
        docRepository.save(doc);
    }

    @Override
    public void saveFilesFromMultiPart(List<MultipartFile> files) {
        files.forEach(file -> {
            try {
                saveFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    @Async
    public void download(Doc doc, HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + doc.getDocName();
        response.setHeader(headerKey, headerValue);
        File file = findFileByDoc(doc);
        try (ServletOutputStream os = response.getOutputStream()) {
            os.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
