package pl.odum.workflowodum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Permit;
import pl.odum.workflowodum.repository.PermitRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class PermitServiceImpl implements PermitService {

    private final PermitRepository permitRepository;

    @Override
    public void save(Permit permit) {
        permitRepository.save(permit);
    }

    @Override
    public void delete(Long id) {
        permitRepository.deleteById(id);
    }

    @Override
    public List<Permit> findAll() {
        return permitRepository.findAll();
    }

    @Override
    public Permit findById(Long id) {
        return permitRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Permit findByPermitName(String name) {
        return permitRepository.findByType("meetings");
    }
}
