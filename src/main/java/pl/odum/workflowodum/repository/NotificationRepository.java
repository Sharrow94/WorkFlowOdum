package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.odum.workflowodum.model.Notification;
import pl.odum.workflowodum.model.User;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query(value = "select n from Notification n where :user member of n.users")
    List<Notification>findAlForUser(User user);
}
