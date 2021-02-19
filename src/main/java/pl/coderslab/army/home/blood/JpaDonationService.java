package pl.coderslab.army.home.blood;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.army.home.order.Order;

import java.util.List;

@Repository
@Primary
public class JpaDonationService implements DonationService {
    private final DonationRepository donationRepository;
    private final DonatorService donatorService;

    public JpaDonationService(DonationRepository donationRepository, DonatorService donatorService) {
        this.donationRepository = donationRepository;
        this.donatorService = donatorService;
    }


    @Override
    public void add(Donation donation) {
        donationRepository.save(donation);
        System.out.println("donation id: " + donation.getId());
        Donator donator = donation.getDonator();
        donator.setAmount(donator.getAmount()+donation.getVolume());
        donatorService.update(donator);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Donation get(Long id) {
        return null;
    }

    @Override
    public List<Donation> donationBySoldier(Donator donator) {
        return donationRepository.getAllByDonatorIdOrderByDateDesc(donator.getId());
    }

    @Override
    public List<Donation> donationBySoldier(Long soldier) {
        return donationRepository.getAllByDonatorIdOrderByDateDesc(soldier);
    }

    @Override
    public List<Donation> getAll() {
        return null;
    }


}
