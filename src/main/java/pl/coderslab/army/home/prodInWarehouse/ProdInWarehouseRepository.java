package pl.coderslab.army.home.prodInWarehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.warehouse.Warehouse;

public interface ProdInWarehouseRepository extends JpaRepository<ProdInWarehouse, Long> {

public ProdInWarehouse findByProductAndWarehouse(Product product, Warehouse warehouse);
}


