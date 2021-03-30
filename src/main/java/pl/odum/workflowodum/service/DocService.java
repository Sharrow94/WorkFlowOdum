package pl.odum.workflowodum.service;

import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Meeting;
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
    void saveFile(MultipartFile file,Client client,Permit permit) throws IOException;
    void saveFilesFromMultiPart(List<MultipartFile> files,Client client,Permit permit);
    void download(Doc doc, HttpServletResponse response);
    void prepareDocToRemoving(Long id);
    void removeDocs();
    void downloadMergedClientsDocx(Client client, HttpServletResponse response);
    void downloadAll(HttpServletResponse response);
    void addNotesToMeeting(List<MultipartFile> files, Meeting meeting);
}
