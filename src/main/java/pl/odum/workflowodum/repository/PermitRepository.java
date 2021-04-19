package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.odum.workflowodum.model.Permit;

import java.util.List;

public interface PermitRepository extends JpaRepository<Permit,Long> {
    Permit findByType(String type);

    @Query(value = "select distinct id,type from doc join permit p on doc.permit_id = p.id where client_id=?1",nativeQuery = true)
    List<Permit>findAllExistForClient(Long id);
}
