package pl.odum.workflowodum.utils;

import org.springframework.stereotype.Component;
import pl.odum.workflowodum.model.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ClientDirectoryCreator {
    public void createDirectoryForClient(Client client) throws IOException {
        Path path = Paths.get(client.getHomePath());
        Files.createDirectories(path);
    }
}
