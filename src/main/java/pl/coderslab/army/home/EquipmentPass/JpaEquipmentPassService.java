package pl.coderslab.army.home.EquipmentPass;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaEquipmentPassService implements EquipmentPassService {

    private final EquipmentPassRepository repository;

    public JpaEquipmentPassService(EquipmentPassRepository repository) {
        this.repository = repository;
    }


    @Override
    public EquipmentPass get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<EquipmentPass> getEquipmentPasses() {
        return repository.findAll();
    }

    @Override
    public void add(EquipmentPass equipmentPass) {

        repository.save(equipmentPass);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void update(EquipmentPass equipmentPass) {
        repository.save(equipmentPass);

    }
}




