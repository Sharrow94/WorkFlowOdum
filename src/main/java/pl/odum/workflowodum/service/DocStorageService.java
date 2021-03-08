package pl.odum.workflowodum.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;
import pl.odum.workflowodum.repository.DocRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocStorageService {
    private final String baseSource = "/home/mcs/IdeaProjects/odum-docs";
    private final DocRepository docRepository;

    public DocStorageService(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    public List<Doc> getFiles(){
        return docRepository.findAll();
    }

    public Doc saveFile(MultipartFile file) throws IOException {
        Doc doc = new Doc();
        doc.setUuid(UUID.randomUUID().toString());
        doc.setDocName(file.getOriginalFilename());
        doc.setDocType(file.getContentType());
        doc.setDateOfAdding(LocalDate.now());


        file.transferTo(new File(baseSource+"/"+doc.getUuid()));
        docRepository.save(doc);
        return doc;
    }

    public Doc findByUuid(String uuid){
        return docRepository.findByUuid(uuid);
    }

    public File findFileByUuid(String uuid){
        return new File(baseSource+"/"+uuid);
    }

    public Doc findByDocNameAndClientAndPermit(String docName, Client client, Permit permit){
        return docRepository.findByDocNameAndClientAndPermit(docName, client, permit);
    }

}
