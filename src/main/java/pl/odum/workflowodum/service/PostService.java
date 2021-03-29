package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Post;

import java.util.List;


public interface PostService {
    void add(Post post);
    void delete(Long id);
    List<Post> postList();
    Post findById(Long id);
}
