package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DocRepository extends JpaRepository<Doc, Long> {
    List<Doc>findAllByDateOfRemovingBeforeAndDateOfRemovingIsNotNull(LocalDate localDate);
    Optional<Doc>findFirstByDocNameAndSourcePath(String docName,String sourcePath);
}
