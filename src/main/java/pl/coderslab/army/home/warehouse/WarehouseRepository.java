package pl.coderslab.army.home.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query(value = "select w from Warehouse w order by w.name")
    public List<Warehouse> getAllOrderByName();


}


