package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<User> users;
    private String description;
    @OneToOne
    private Meeting meeting;

    public void setDescription(){
        if (this.meeting!=null){
            this.description="Brak notatki dla spotkania z klientem: "+this.meeting.getClient().getName()+" z dnia: "+this.meeting.getDateOfMeeting();
        }else
            throw new IllegalArgumentException("Meeting is null");

    }
}
