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
    private String description;
    private LocalDate localDate;
    private boolean isRead;

    public void setDescription(String clientName,LocalDate date){
        this.description="Brak notatki dla spotkania z klientem: "+clientName+" z dnia: "+date;
    }
}
