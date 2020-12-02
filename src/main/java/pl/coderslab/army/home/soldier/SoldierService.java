package pl.coderslab.army.home.soldier;

import java.util.List;

public interface SoldierService {
    public Soldier get(Long id);

    public List<Soldier> getSoldiers();

    public Soldier getSoldier(String email);

    public void add(Soldier soldier);

    public void delete(Long id);

    public void update(Soldier soldier);
}
