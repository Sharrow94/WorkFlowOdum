package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.odum.workflowodum.email.Receiver;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class ClientEmployee extends Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
