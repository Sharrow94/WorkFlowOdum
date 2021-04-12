package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.ClientEmployee;
import pl.odum.workflowodum.repository.ClientEmployeeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientEmployeeService {

    private final ClientEmployeeRepository clientEmployeeRepository;

    public List<ClientEmployee> findAllForClient(Long id){
        return clientEmployeeRepository.findEmployeesForClient(id);
    }
}
