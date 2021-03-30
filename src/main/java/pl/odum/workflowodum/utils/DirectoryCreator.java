package pl.odum.workflowodum.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Permit;
import pl.odum.workflowodum.service.ClientService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@AllArgsConstructor
public class DirectoryCreator {

    public void createDirectoryForClient(Client client) throws IOException {
        Path path = Paths.get(client.getHomePath());
        Files.createDirectories(path);
    }

    public void createDirectoryPermitForClient(Client client, Permit permit) throws IOException{
        Path path=Paths.get(client.getHomePath()+"/"+permit.getType());
        Files.createDirectories(path);
    }

}
