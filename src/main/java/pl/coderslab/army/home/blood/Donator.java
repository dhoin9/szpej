package pl.coderslab.army.home.blood;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.army.home.soldier.Soldier;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Donator {
    @Id
    @Column(name="soldier_id")
    private long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "soldier_id")
    private Soldier soldier;
    private String bloodType;
    private float amount;

    @Override
    public String toString() {
        return "Donator{" +
                "id=" + id +
                ", soldier=" + soldier.getLastName() +
                ", bloodType='" + bloodType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
