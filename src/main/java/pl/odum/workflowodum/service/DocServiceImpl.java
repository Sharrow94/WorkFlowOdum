package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.repository.DocRepository;

public class DocServiceImpl implements DocService {

    private final DocRepository docRepository;

    public DocServiceImpl(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    @Override
    public void save(Doc doc) {
        docRepository.save(doc);
    }

    @Override
    public void delete(String id) {
        docRepository.deleteById(id);
    }

    @Override
    public Doc findById(Long id) {
        return null;
    }
}
