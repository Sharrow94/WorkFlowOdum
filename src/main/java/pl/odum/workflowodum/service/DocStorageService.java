package pl.odum.workflowodum.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.repository.DocRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DocStorageService {

    private final DocRepository docRepository;

    public DocStorageService(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    public Doc saveFile(MultipartFile file) {
        String docName = file.getOriginalFilename();
        try {
            Doc doc = new Doc(docName,file.getContentType(),file.getBytes());
            return docRepository.save(doc);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Doc> getFile(Long fileId) {
        return docRepository.findById(fileId);
    }

    public List<Doc> getFiles(){
        return docRepository.findAll();
    }
}
