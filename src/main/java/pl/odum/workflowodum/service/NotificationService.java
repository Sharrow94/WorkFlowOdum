package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Notification;
import pl.odum.workflowodum.model.User;

import java.util.List;

public interface NotificationService {

    void save();
    void delete(Notification notification);

    List<Notification> findAll();
    List<Notification> findAllForAdmin(User admin);
    List<Notification> findAllForAdmin(String username);
    List<Notification> findAllForUser(User user);
    List<Notification> findAllForUser(String username);
    Notification findFirstByMeeting(Meeting meeting);

}
