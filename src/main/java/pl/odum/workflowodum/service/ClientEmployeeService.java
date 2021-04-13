package pl.odum.workflowodum.service;

import pl.odum.workflowodum.email.EmailStatus;
import pl.odum.workflowodum.model.ClientEmployee;

import java.util.List;

public interface ClientEmployeeService {
    List<ClientEmployee> findAllForClient(Long id);
    void save(ClientEmployee clientEmployee);
    ClientEmployee findToAddToClient(String firstName,String lastName,String email);
    ClientEmployee findById(Long id);
    EmailStatus sendEmailWithAttachment(Long id, String docUUID);
}
