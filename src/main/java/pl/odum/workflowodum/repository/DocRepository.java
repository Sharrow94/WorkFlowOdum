package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DocRepository extends JpaRepository<Doc, Long> {
    List<Doc>findAllByDateOfRemovingBeforeAndDateOfRemovingIsNotNull(LocalDate localDate);
    Optional<Doc>findFirstByDocNameAndSourcePath(String docName,String sourcePath);
    Doc findByUuid(String uuid);
    Doc deleteByUuid(String uuid);
    @Query(value = "select d from Doc d where d.permit.type='meetings' and d.client=?1")
    List<Doc>findAllForClientMeetings(Client client);
    List<Doc>findAllByPermitIdAndClientIdAndDateOfRemovingIsNull(Long permitId,Long clientId);
    List<Doc>findAllByClientIdAndDateOfRemovingIsNotNull(Long id);
}
