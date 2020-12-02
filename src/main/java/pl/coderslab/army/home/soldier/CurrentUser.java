package pl.coderslab.army.home.soldier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.army.home.soldier.Soldier;

import java.util.Collection;

public class CurrentUser extends User {
    private final Soldier soldier;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       Soldier soldier) {
        super(username, password, authorities);
        this.soldier = soldier;
    }

    public Soldier getSoldier() {
        return soldier;
    }
}
