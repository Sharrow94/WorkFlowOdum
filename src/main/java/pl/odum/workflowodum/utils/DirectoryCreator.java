package pl.odum.workflowodum.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Permit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@AllArgsConstructor
public class DirectoryCreator {

    public void createDirectoryForClient(Client client) throws IOException {
        Path path = Paths.get(client.getHomePath());
        Files.createDirectories(path);
    }

    public void createDirectoryPermitForClient(String pathForDir) throws IOException{
        Path path=Paths.get(pathForDir);
        Files.createDirectories(path);
    }

}
