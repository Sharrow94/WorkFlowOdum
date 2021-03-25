package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Notification;

import java.util.List;

public interface NotificationService {

    void save();
    void delete(Long id);
    List<Notification>findAll();
}
