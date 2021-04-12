package pl.odum.workflowodum.email;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Receiver {
    private String email;
    private String firstName;
    private String lastName;

    public static void main(String[] args) {
        MyMailMessage myMailMessage = MyMailMessage.builder()
                .receiver(new Receiver())
                .subject("gfdgf")
                .build();

        System.out.println(myMailMessage.getMessage());
    }
}
