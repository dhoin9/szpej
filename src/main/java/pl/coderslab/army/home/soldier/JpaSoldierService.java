package pl.coderslab.army.home.soldier;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaSoldierService implements SoldierService {

    private final SoldierRepository repository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public JpaSoldierService(SoldierRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
    public Soldier getSoldier(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void add(Soldier soldier) {
        soldier.setPassword(passwordEncoder.encode(soldier.getPassword()));
        soldier.setEnabled(1);
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




