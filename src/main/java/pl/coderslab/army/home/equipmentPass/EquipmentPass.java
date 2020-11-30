package pl.coderslab.army.home.equipmentPass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.warehouse.Warehouse;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private LocalDate expireDate;

    @Override
    public String toString() {
        return "EquipmentPass{" +
                "product name=" + product.getName() +
                "product size=" + product.getSize() +
                ", quantity=" + quantity +
                '}';
    }


}
