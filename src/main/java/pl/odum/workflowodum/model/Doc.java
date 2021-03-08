package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String docName;
    private String docType;
    private LocalDate dateOfAdding;
    private LocalDateTime dateOfLastEdit;
    private Long userAddingId;
    private Long userEditingId;
    private boolean isOk;

    @Lob
    private byte[] data;

    public Doc(String docName, String docType, byte[] data) {
        this.docName = docName;
        this.docType=docType;
        this.data = data;
    }
}
