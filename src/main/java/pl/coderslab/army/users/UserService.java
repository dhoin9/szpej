package pl.coderslab.army.users;

public interface UserService {

    AppUser findByUserName(String name);

    void saveUser(AppUser appUser);
}
