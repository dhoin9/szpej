package pl.coderslab.army.home.soldier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    public List<Role> findAll();
}
