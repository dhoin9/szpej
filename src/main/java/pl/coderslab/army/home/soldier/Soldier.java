package pl.coderslab.army.home.soldier;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.army.home.warehouse.Warehouse;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min=2)
    private String firstName;
    @Size(min=2)
    private String lastName;
    @Column(nullable = false, unique = true, length = 60)
    @Email
    private String email;
    private String password;
    private int enabled;
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name="soldier_roles",joinColumns = {@JoinColumn(name = "soldier_id")},
//    inverseJoinColumns = {@JoinColumn(name = "roles_id")})
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
