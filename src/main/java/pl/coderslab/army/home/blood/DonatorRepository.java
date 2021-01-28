package pl.coderslab.army.home.blood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonatorRepository extends JpaRepository<Donator, Long> {


    @Query(value ="SELECT donator.id from donator join soldier s on s.id = donator.soldier_id where s.warehouse_id=?1 ",
            nativeQuery = true)
    List<String> allDonorsByUnit(long warehouse);

    Donator getById(Long id);


}
