package pl.coderslab.army.home.equipmentPass;

import pl.coderslab.army.home.soldier.Soldier;

import java.util.List;

public interface EquipmentPassService {
    public EquipmentPass get(Long id);

    public List<EquipmentPass> getEquipmentPasses();
    public List<EquipmentPass> getEquipmentPasses(Soldier soldier);

    public void add(EquipmentPass equipmentPass);

    public void delete(Long id);

    public void update(EquipmentPass equipmentPass);
}
