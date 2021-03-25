package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.Notification;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.MeetingRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final NotificationService notificationService;

    public MeetingServiceImpl(MeetingRepository meetingRepository, NotificationService notificationService) {
        this.meetingRepository = meetingRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void save(Meeting meeting) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Meeting> findAllForUser() {
        return null;
    }

    @Override
    public Meeting findById(Long id) {
        return null;
    }

    @Override
    public List<Meeting> findAllOutOfDate() {
        return meetingRepository.findAllByDateOfMeetingBefore(LocalDate.now());
    }

    public void createNotifications(){
        List<Meeting>meetings=findAllOutOfDate();
        List<User>usersForNotification=new ArrayList<>();
        meetings.forEach(meeting->{
            Notification notification=new Notification();
            notification.setLocalDate(LocalDate.now());
            usersForNotification.add(meeting.getUser());
            notification.setUsers(usersForNotification);
            notification.setMeeting(meeting);
            notificationService.save(notification);
        });
    }
}
