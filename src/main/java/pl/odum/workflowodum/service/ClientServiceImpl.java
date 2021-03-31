package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.repository.ClientRepository;
import pl.odum.workflowodum.utils.DirectoryCreator;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final static String USERS_BASE_PATH = "/home/maciej/odum-docs/clients";
    private final ClientRepository clientRepository;
    private final DirectoryCreator directoryCreator;

    @Override
    @Transactional
    public void save(Client client){
        client.setHomePath(USERS_BASE_PATH+"/"+client.getName().toLowerCase());
        try {
            directoryCreator.createDirectoryForClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientRepository.save(client);
    }

    @Override
    public void edit(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(()->new IllegalStateException("Client not found"));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
