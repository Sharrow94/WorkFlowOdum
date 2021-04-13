package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.odum.workflowodum.model.Post;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post>findAllByOrderByDateOfPostDesc();
    Post findByDateOfPostAndTitleAndAndDescription(LocalDate date,String title,String description);
}
