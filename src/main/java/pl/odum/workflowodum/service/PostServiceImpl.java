package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Post;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.DocRepository;
import pl.odum.workflowodum.repository.PermitRepository;
import pl.odum.workflowodum.repository.PostRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ClientService clientService;
    private final PermitRepository permitRepository;
    private final DocRepository docRepository;


    @Override
    public void add(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> postList() {
        return postRepository.findAllByOrderByDateOfPostDesc();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Post does not exists"));
    }

    @Override
    public Post findByDateOfPostAndTitleAndAndDescription(LocalDate date, String title, String description) {
        return postRepository.findByDateOfPostAndTitleAndAndDescription(date, title, description);
    }

    @Override
    public void addDocsToPost(List<MultipartFile> files, Post post, User user) {
        files.forEach(file -> {
            try {
                addDocToPost(file, post, user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void addDocToPost(MultipartFile file, Post post, User user) throws IOException {

        Doc doc = new Doc();
        doc.setUuid(UUID.randomUUID().toString());
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());
        doc.setTimeOfAdding(LocalTime.now());
        doc.setClient(clientService.findByName("Odum"));
        doc.setUserAddingId(user.getId());
        doc.setPermit(permitRepository.findByType("posty"));
        doc.setSourcePath(clientService.findByName("Odum").getHomePath() + "/posty");

        Files.createDirectories(Paths.get(doc.getSourcePath()));


        file.transferTo(doc.getFile());


        Post editedPost=findById(post.getId());
        editedPost.setDescription(post.getDescription());
        editedPost.setTitle(post.getTitle());
        List<Doc>docs=editedPost.getDocs();
        docs.add(doc);

        docRepository.save(doc);

        postRepository.save(editedPost);
    }
}
