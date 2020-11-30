package pl.coderslab.army.home.equipmentPass;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.soldier.Soldier;

import java.time.LocalDate;
import java.util.List;

@Repository
@Primary
public class JpaEquipmentPassService implements EquipmentPassService {

    private final EquipmentPassRepository repository;
    private final ProdInWarehouseService prodInWarehouseService;

    public JpaEquipmentPassService(EquipmentPassRepository repository, ProdInWarehouseService prodInWarehouseService) {
        this.repository = repository;
        this.prodInWarehouseService = prodInWarehouseService;
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
    public List<EquipmentPass> getEquipmentPasses(Soldier soldier) {
        return repository.findAllBySoldier(soldier);
    }

    @Override
    public void add(EquipmentPass equipmentPass) {
        int years= equipmentPass.getProduct().getLast();
        equipmentPass.setExpireDate(LocalDate.now().plusYears(years));
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



