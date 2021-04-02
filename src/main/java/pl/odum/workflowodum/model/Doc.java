package pl.odum.workflowodum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String sourcePath;
    @ManyToOne
    private Permit permit;
    private boolean isOk;
    @ManyToOne
    private Client client;
    private boolean toRemove;
    private LocalDate dateOfRemoving;

    public String fullPath(){
        if(this.sourcePath==null || this.docName==null){
            throw new IllegalStateException("Source Path Or docName is empty!");
        }
        return this.sourcePath+"/"+this.uuid;
    }

    public File getFile(){
        return new File(fullPath());
    }
}
