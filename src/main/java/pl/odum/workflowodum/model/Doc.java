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
    private String sourcePath;
    @ManyToOne
    private Permit permit;
    private boolean isOk;
    private boolean toRemove;
    private LocalDate dateOfRemoving;
}
