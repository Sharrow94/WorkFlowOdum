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
    private LocalDate localDate;
    @OneToOne
    private Meeting meeting;

    public void setDescription(String clientName,LocalDate date){
        this.description="Brak notatki dla spotkania z klientem: "+clientName+" z dnia: "+date;
    }
}