package pl.coderslab.army.home.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.soldier.Soldier;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Product product;
    private int quantity;
    @ManyToOne
    private Soldier soldier;
    private boolean active;

}
