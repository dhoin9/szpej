package pl.coderslab.army.home.soldier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldierRepository extends JpaRepository<Soldier, Long> {

    Soldier findByEmail(String email);
}


