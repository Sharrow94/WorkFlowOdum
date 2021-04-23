package pl.odum.workflowodum.service;

import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Post;
import pl.odum.workflowodum.model.User;

import java.time.LocalDate;
import java.util.List;


public interface PostService {
    void add(Post post);
    void delete(Long id);
    List<Post> postList();
    Post findById(Long id);
    Post findByDateOfPostAndTitleAndAndDescription(LocalDate date, String title, String description);
    void addDocsToPost(List<MultipartFile> files, Post post, User user);
    Post findPostByDoc(Doc doc);
}
