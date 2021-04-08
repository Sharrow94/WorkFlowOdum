package pl.odum.workflowodum.service;

import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Notification;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final MeetingService meetingService;
    private final UserService userService;

    public NotificationServiceImpl(NotificationRepository notificationRepository, MeetingService meetingService, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.meetingService = meetingService;
        this.userService = userService;
    }

    @Override
    public void save() {
        List<Meeting> meetings = meetingService.findAllOutOfDate();
        List<User> usersForNotification = userService.findAllAdmins();
        meetings.forEach(meeting -> {
            if (notificationRepository.findFirstByMeeting_Id(meeting.getId()) == null) {
                Notification notification = new Notification();
                usersForNotification.add(meeting.getUser());
                notification.setUsers(usersForNotification);
                notification.setMeeting(meeting);
                notification.setDescription();
                notificationRepository.save(notification);
            }
        });
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Notification> findAll() {
        return null;
    }

    @Override
    public List<Notification> findAllForAdmin(String username) {
        User user = userService.findByUserName(username);
        return findAllForAdmin(user);
    }

    @Override
    public List<Notification> findAllForAdmin(User admin) {
        return notificationRepository.findAllForAdmin(admin);
    }

    @Override
    public List<Notification> findAllForUser(String username) {
        User user = userService.findByUserName(username);
        return findAllForUser(user);
    }

    @Override
    public List<Notification> findAllForUser(User user) {
        return notificationRepository.findAllForUser(user);
    }

    @Override
    public Notification findFirstByMeeting(Meeting meeting) {
        return notificationRepository.findFirstByMeeting(meeting);
    }
}
