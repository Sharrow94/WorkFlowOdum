package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfMeeting;
    @ManyToOne
    private Client client;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Doc> doc;
    private int countOfDocs;

    public void updateCountOfDocs(){
        this.countOfDocs = getCountOfDocs();
    }

    public int getCountOfDocs(){
        int size = (int) doc.stream().filter(d -> d.getDateOfRemoving() == null).count();
        System.out.println(size);
        return size;
    }
}
