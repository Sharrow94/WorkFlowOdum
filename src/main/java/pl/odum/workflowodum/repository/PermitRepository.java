package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.odum.workflowodum.model.Permit;

public interface PermitRepository extends JpaRepository<Permit,Long> {
}
