package pl.coderslab.army.home.blood;

import pl.coderslab.army.home.order.Order;

import java.util.List;

public interface DonationService {

    void add(Donation donation);
    void delete(Long id);
    void update(Order order);
    Donation get(Long id);
    List<Donation> donationBySoldier(Donator donator);
    List<Donation> getAll();
}
