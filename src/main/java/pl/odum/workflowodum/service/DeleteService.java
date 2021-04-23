package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.DownloadLog;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Post;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@AllArgsConstructor
public class DeleteService {

    private final DocService docService;
    private final MeetingService meetingService;
    private final DownloadLogService downloadLogService;
    private final PostService postService;

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
        Meeting meeting=meetingService.findByDoc(doc);
        List<Doc>meetingDocs=meeting.getDoc();
        meetingDocs.remove(doc);
        meeting.setDoc(meetingDocs);
        meetingService.save(meeting);
    }

    private void removeDocFromPost(Doc doc){
        Post post=postService.findPostByDoc(doc);
        List<Doc>postDocs=post.getDocs();
        postDocs.remove(doc);
        post.setDocs(postDocs);
        postService.add(post);
    }

    private void removeDoc(Doc doc){
        try {
            Files.delete(Path.of(doc.fullPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        docService.deleteDoc(doc);
    }
}
