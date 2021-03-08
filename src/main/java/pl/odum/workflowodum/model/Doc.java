package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Doc {
    @Id
    private String uuid;
    private String docName;
    private String docType;
    private LocalDate dateOfAdding;
    private LocalDateTime dateOfLastEdit;
    private Long userAddingId;
    private Long userEditingId;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Permit permit;
    private boolean isOk;
}
