package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Notification;
import pl.odum.workflowodum.model.User;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query(value = "select n from Notification n where ?1 member of n.users")
    List<Notification>findAllForAdmin(User user);
    Notification findFirstByMeeting_Id(Long id);
    @Query(value = "select n from Notification n where n.meeting.user=?1")
    List<Notification>findAllForUser(User user);
    Notification findFirstByMeeting(Meeting meeting);
}
