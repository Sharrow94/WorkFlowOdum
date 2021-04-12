package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Post;
import pl.odum.workflowodum.repository.PostRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;


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
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Post does not exists"));
    }
}
