package pl.odum.workflowodum.service;

import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Permit;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DocService {
    List<Doc> getFiles();
    Doc findById(Long id);
    void saveFile(MultipartFile file,Client client,Permit permit,Long userId) throws IOException;
    void saveFilesFromMultiPart(List<MultipartFile> files,Client client,Permit permit,Long id);
    void download(Doc doc, HttpServletResponse response);
    void prepareDocToRemoving(String uuid);
    void removeDocs();
    void downloadMergedClientsDocx(Client client, HttpServletResponse response) throws IOException;
    void addNotesToMeeting(List<MultipartFile> files, Meeting meeting);
}
