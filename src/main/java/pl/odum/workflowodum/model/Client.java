package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Email
    private String email;
    @OneToMany
    private List<Permit>permits;
}
