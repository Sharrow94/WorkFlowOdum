package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.ClientEmployee;

import java.io.FileNotFoundException;
import java.util.List;

public interface ClientService {

    void save(Client client) throws FileNotFoundException;
    void edit(Client client);
    void delete(Client client);
    Client findById(Long id);
    List<Client> findAll();
    void addEmployeeToClient(Long id, ClientEmployee employee);
    Client findByName(String name);

}
