package pl.odum.workflowodum.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.odum.workflowodum.model.Role;
import pl.odum.workflowodum.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
    @Query(value = "select u from User u where  :role member of u.roles")
    List<User> findAllByRoleAdmin(Role role);
}
