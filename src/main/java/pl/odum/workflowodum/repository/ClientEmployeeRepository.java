package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.odum.workflowodum.model.ClientEmployee;

import java.util.List;

@Repository
public interface ClientEmployeeRepository extends JpaRepository<ClientEmployee,Long> {

    @Query(value = "select c.employees from Client c where c.id=?1")
    List<ClientEmployee>findEmployeesForClient(Long id);

    ClientEmployee findByFirstNameAndAndLastNameAndEmail(String firstName,String lastName,String email);
}
