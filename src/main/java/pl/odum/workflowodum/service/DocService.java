package pl.odum.workflowodum.service;

import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface DocService {
    List<Doc> getFiles();
    Doc findById(Long id);
    File findFileByClientAndFileName(Client client, String name);
    File findFileByDoc(Doc doc);
    Doc findByDocNameAndClientAndPermit(String docName, Client client, Permit permit);
    void saveFile(MultipartFile file) throws IOException;
    void saveFilesFromMultiPart(List<MultipartFile> files);
    void download(Doc doc, HttpServletResponse response);
    void prepareDocToRemoving(Long id);
    void removeDocs();
    void downloadMergedClientsDocx(Client client, HttpServletResponse response);
    Set<Doc> findAllByClient(Client client);
    void downloadAll(HttpServletResponse response);
}
