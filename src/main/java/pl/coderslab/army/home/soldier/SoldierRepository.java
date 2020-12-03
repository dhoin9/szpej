package pl.coderslab.army.home.soldier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoldierRepository extends JpaRepository<Soldier, Long> {

    Soldier findByEmail(String email);


    List<Soldier> getAllByRoles(Role role);
}


