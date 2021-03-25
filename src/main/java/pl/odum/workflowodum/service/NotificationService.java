package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Notification;

import java.util.List;

public interface NotificationService {

    void save(Notification notification);
    void delete(Long id);
    List<Notification>findAll();
}
