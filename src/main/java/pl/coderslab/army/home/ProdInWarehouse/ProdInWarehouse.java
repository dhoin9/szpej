package pl.coderslab.army.home.ProdInWarehouse;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.warehouse.Warehouse;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProdInWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Product product;
    private int quantity;

}
