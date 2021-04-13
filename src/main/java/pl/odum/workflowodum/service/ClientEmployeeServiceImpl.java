package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.email.EmailService;
import pl.odum.workflowodum.email.MyMailMessage;
import pl.odum.workflowodum.model.ClientEmployee;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.repository.ClientEmployeeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientEmployeeServiceImpl implements ClientEmployeeService {
    private final ClientEmployeeRepository clientEmployeeRepository;
    private final DocService docService;
    private final EmailService emailService;

    @Override
    public ClientEmployee findById(Long id) {
        return clientEmployeeRepository.findById(id).orElseThrow(()->
                new IllegalStateException("ClientEmployee with id="+id+" does not exists!"));
    }

    @Override
    public List<ClientEmployee> findAllForClient(Long id){
        return clientEmployeeRepository.findEmployeesForClient(id);
    }

    @Override
    public void save(ClientEmployee clientEmployee){
        clientEmployeeRepository.save(clientEmployee);
    }

    @Override
    public ClientEmployee findToAddToClient(String firstName,String lastName,String email){
        return clientEmployeeRepository.findByFirstNameAndAndLastNameAndEmail(firstName, lastName, email);
    }

    @Override
    public void sendEmailWithAttachment(Long id, String docUUID) {
        ClientEmployee employee = findById(id);
        Doc doc = docService.findByUuid(docUUID);
        MyMailMessage mailMessage = MyMailMessage.builder()
                .receiver(employee)
                .attachment(doc)
                .subject("Załączona notatka ze spotkania")
                .build();

        emailService.send(mailMessage);

    }
}
