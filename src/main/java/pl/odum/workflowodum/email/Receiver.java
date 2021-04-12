package pl.odum.workflowodum.email;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Receiver {
    private String email;
    private String firstName;
    private String lastName;
}
