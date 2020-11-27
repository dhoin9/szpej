package pl.coderslab.army.home.equipmentPass;

import java.util.ArrayList;
import java.util.List;

public class EquipmentPassList {
    private List<EquipmentPass> equipmentPassList;


    public EquipmentPassList(){
        this.equipmentPassList= new ArrayList<EquipmentPass>();
    }

    public List<EquipmentPass> getEquipmentPassList() {
        return equipmentPassList;
    }

    public void setEquipmentPassList(List<EquipmentPass> equipmentPassList) {
        this.equipmentPassList = equipmentPassList;
    }
    public void add(EquipmentPass equipmentPass){
        this.equipmentPassList.add(equipmentPass);
    }
}
