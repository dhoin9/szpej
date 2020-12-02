package pl.coderslab.army.home.soldier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    private SoldierService soldierService;

    @Autowired
    public void setSoldierRepository(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Soldier  soldier = soldierService.getSoldier(email);
        if (soldier == null) {
            throw new UsernameNotFoundException(email);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        soldier.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new CurrentUser(soldier.getEmail(), soldier.getPassword(),
                grantedAuthorities, soldier);
    }
}
