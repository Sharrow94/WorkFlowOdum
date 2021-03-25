package pl.odum.workflowodum.service;

import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.MeetingRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;


    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;

    }

    @Override
    public void save(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    @Override
    public void delete(Long id) {
        meetingRepository.deleteById(id);
    }

    @Override
    public List<Meeting> findAllForUser(User user) {
        return meetingRepository.findAllByUser(user);
    }

    @Override
    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Meeting> findAllOutOfDate() {
        return meetingRepository.findAllByDateOfMeetingBefore(LocalDate.now());
    }
}
