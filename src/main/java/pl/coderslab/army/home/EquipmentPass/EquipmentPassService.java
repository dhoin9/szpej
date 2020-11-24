package pl.coderslab.army.home.EquipmentPass;

import java.util.List;

public interface EquipmentPassService {
    public EquipmentPass get(Long id);

    public List<EquipmentPass> getEquipmentPasses();

    public void add(EquipmentPass equipmentPass);

    public void delete(Long id);

    public void update(EquipmentPass equipmentPass);
}
