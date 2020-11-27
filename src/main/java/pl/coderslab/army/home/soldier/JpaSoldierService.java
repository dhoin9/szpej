package pl.coderslab.army.home.soldier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.army.home.users2.UserService;

import java.util.List;

@Repository
@Primary
public class JpaSoldierService implements SoldierService {

    private final SoldierRepository repository;
    private final UserService userService;

    public JpaSoldierService(SoldierRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
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
        userService.saveUser(soldier);
//        repository.save(soldier);
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




