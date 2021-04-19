package pl.odum.workflowodum.service;

import pl.odum.workflowodum.model.Permit;

import java.util.List;

public interface PermitService {

    void save(Permit permit);
    void delete(Long id);
    List<Permit>findAll();
    Permit findById(Long id);
    Permit findByPermitName(String name);
    List<Permit>findAllExistForClient(Long id);
}
