package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nip;
    private String street;
    private Long streetNr;
    private String city;
    private String zipCode;
    private String homePath;
//    @Email
    private String email;
    @OneToMany
    private List<Doc>docs;

    public void setHomePath(String homePath){
        this.homePath = homePath.strip().replace(" ", "-");
    }
}
