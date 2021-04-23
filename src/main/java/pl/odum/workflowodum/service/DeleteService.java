package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.DownloadLog;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Post;
import pl.odum.workflowodum.repository.DocRepository;
import pl.odum.workflowodum.repository.MeetingRepository;
import pl.odum.workflowodum.repository.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@AllArgsConstructor
public class DeleteService {

    private final DocRepository docRepository;
    private final MeetingRepository meetingRepository;
    private final DownloadLogService downloadLogService;
    private final PostRepository postRepository;

    public void whenDeletingDoc(Doc doc){
        removeAllLogsWithDoc(doc);
        removeDocFromMeeting(doc);
        removeDocFromPost(doc);
        removeDoc(doc);
    }

    private void removeAllLogsWithDoc(Doc doc){
        List<DownloadLog>logs=downloadLogService.findAllLogsForDoc(doc);
        logs.forEach(downloadLogService::deleteLog);
    }

    private void removeDocFromMeeting(Doc doc){
        Meeting meeting= meetingRepository.findByDoc(doc);
        if (meeting!=null){
            List<Doc>meetingDocs=meeting.getDoc();
            meetingDocs.remove(doc);
            meeting.setDoc(meetingDocs);
            meetingRepository.save(meeting);
        }
    }

    private void removeDocFromPost(Doc doc){
        Post post=postRepository.findPostByDoc(doc);
        if (post!=null){
            List<Doc>postDocs=post.getDocs();
            postDocs.remove(doc);
            post.setDocs(postDocs);
            postRepository.save(post);
        }
    }

    private void removeDoc(Doc doc){
        try {
            Files.delete(Path.of(doc.fullPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        docRepository.delete(doc);
    }
}
