package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Doc;

public interface DocService {

    void save(Doc doc);
    void delete(String id);
    Doc findById(Long id);

}
