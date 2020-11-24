package pl.coderslab.army.home.EquipmentPass;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.warehouse.Warehouse;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class EquipmentPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Soldier soldier;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Warehouse warehouse;
    private int quantity;

}
