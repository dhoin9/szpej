package pl.coderslab.army.home.blood;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    Donation getById(Long id);
    List<Donation> getAllByDonatorIdOrderByDateDesc(Long donator);
    List<Donation> getAllByDate(LocalDate date);


}
