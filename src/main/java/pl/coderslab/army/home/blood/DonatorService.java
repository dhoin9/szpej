package pl.coderslab.army.home.blood;

import java.util.List;

public interface DonatorService {

    void add(Donator donator);
    void delete(Long id);
    void update(Donator donator);
    Donator get(Long id);
    List<Donator> donorByUnit(Long id);
    List<Donator> getAll();
}
