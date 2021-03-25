package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.User;

import java.util.List;

public interface MeetingService {
    void save(Meeting meeting);
    void delete(Long id);
    List<Meeting>findAllForUser(User user);
    Meeting findById(Long id);
    List<Meeting> findAllOutOfDate();
}
