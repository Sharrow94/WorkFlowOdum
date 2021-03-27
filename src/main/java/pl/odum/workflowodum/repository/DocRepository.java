package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;

import java.time.LocalDate;
import java.util.List;

public interface DocRepository extends JpaRepository<Doc, Long> {
    Doc findByDocNameAndClientAndPermit(String docName, Client client, Permit permit);
    List<Doc>findAllByDateOfRemovingBeforeAndDateOfRemovingIsNotNull(LocalDate localDate);
}
