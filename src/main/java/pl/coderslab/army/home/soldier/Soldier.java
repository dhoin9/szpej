package pl.coderslab.army.home.soldier;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.army.home.warehouse.Warehouse;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne
    private Warehouse warehouse;

    @Override
    public String toString() {
        return "Soldier{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", warehouse=" + warehouse.getName() +
                '}';
    }
}
