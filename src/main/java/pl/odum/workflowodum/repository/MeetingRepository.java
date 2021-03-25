package pl.odum.workflowodum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.odum.workflowodum.model.Meeting;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    List<Meeting>findAllByDateOfMeetingBefore(LocalDate localDate);
}
