package pl.coderslab.army.home.equipmentPass;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.army.home.soldier.Soldier;

import java.util.List;

public interface EquipmentPassRepository extends JpaRepository<EquipmentPass, Long> {

public List<EquipmentPass> findAllBySoldier(Soldier soldier);


}


