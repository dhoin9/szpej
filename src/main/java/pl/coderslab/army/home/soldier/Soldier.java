package pl.coderslab.army.home.soldier;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.army.home.warehouse.Warehouse;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    private String password;
    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToOne
    private Warehouse warehouse;

    @Override
    public String toString() {
        return "Soldier{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles.toString() +
                ", warehouse=" + warehouse.getName() +
                '}';
    }
}
