package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Notification;
import pl.odum.workflowodum.model.User;

import java.util.List;

public interface NotificationService {

    void save();
    void delete(Long id);
    List<Notification>findAll();
    List<Notification>findAllForAdmin(User user);
    List<Notification>findAllForUser(User user);
    List<Notification>findAllForUser(String username);
}
