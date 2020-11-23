package pl.coderslab.army.home.soldier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaSoldierService implements SoldierService {

    private final SoldierRepository repository;

    public JpaSoldierService(SoldierRepository repository) {
        this.repository = repository;
    }


    @Override
    public Soldier get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Soldier> getSoldiers() {
        return repository.findAll();
    }

    @Override
    public void add(Soldier soldier) {
        repository.save(soldier);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void update(Soldier soldier) {
        repository.save(soldier);

    }
}




