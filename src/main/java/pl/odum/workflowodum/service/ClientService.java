package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Client;

import java.io.FileNotFoundException;
import java.util.List;

public interface ClientService {
    void save(Client client) throws FileNotFoundException;
    void edit(Client client);
    void delete(Client client);
    Client findById(Long id);
    List<Client> findAll();

}
