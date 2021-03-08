package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.odum.workflowodum.model.Doc;

public interface DocRepository extends JpaRepository<Doc,Long> {
}
