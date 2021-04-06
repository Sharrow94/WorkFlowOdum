package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.DownloadLog;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.DownloadLogRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DownloadLogService {

    private final DownloadLogRepository downloadLogRepository;

    void addOrEdit(Doc doc, User user){
        if (downloadLogRepository.findForDocAndUser(doc,user)==null){
            DownloadLog downloadLog=new DownloadLog();
            downloadLog.setDoc(doc);
            downloadLog.setUser(user);
            downloadLog.setDownloadDate(LocalDateTime.now());
            downloadLogRepository.save(downloadLog);
        }else {
            DownloadLog downloadLog=downloadLogRepository.findForDocAndUser(doc, user);
            downloadLog.setDownloadDate(LocalDateTime.now());
            downloadLogRepository.save(downloadLog);
        }
    }
}
