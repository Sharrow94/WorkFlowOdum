package pl.odum.workflowodum.service;

import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Notification;
import pl.odum.workflowodum.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Notification> findAll() {
        return null;
    }
}
