package pl.coderslab.army.home.prodInWarehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.warehouse.Warehouse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProdInWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Product product;
    private int quantity;

    public ProdInWarehouse(Warehouse warehouse, Product product, int quantity) {
        this.warehouse = warehouse;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProdInWarehouse{" +
                "id=" + id +
                ", warehouse=" + warehouse.getName() +
                ", product=" + product.getName()+ " "+ product.getSize()+
                 ", quantity=" + quantity +
                '}';
    }
}
