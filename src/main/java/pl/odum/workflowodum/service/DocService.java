package pl.odum.workflowodum.service;

import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DocService {
    List<Doc> getFiles();
    Doc findByUuid(String uuid);
    void saveFile(MultipartFile file,Client client,Permit permit,Long userId) throws IOException;
    void saveFilesFromMultiPart(List<MultipartFile> files,Client client,Permit permit,Long id);
    void download(Doc doc, HttpServletResponse response, User user);
    void prepareDocToRemoving(String uuid);
    void removeDocs();
    void downloadMergedClientsDocx(Client client, HttpServletResponse response) throws IOException;
    void addNotesToMeeting(List<MultipartFile> files, Meeting meeting);
}
