package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.odum.workflowodum.model.Client;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
