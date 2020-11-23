package pl.coderslab.army.home.products;

import lombok.Getter;
import lombok.Setter;

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
