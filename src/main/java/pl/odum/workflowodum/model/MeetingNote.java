package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class MeetingNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfMeeting;
    @ManyToOne
    private Client client;
    @ManyToOne
    private User user;
    @ManyToOne
    private Doc doc;
}
