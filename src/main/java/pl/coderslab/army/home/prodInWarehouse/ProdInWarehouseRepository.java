package pl.coderslab.army.home.prodInWarehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.List;
import java.util.Map;

public interface ProdInWarehouseRepository extends JpaRepository<ProdInWarehouse, Long> {

    public ProdInWarehouse findByProductAndWarehouse(Product product, Warehouse warehouse);

    public List<ProdInWarehouse> findAllByWarehouse(Warehouse warehouse);

    public List<ProdInWarehouse> findAllByProduct(Product product);

    @Query(value = "SELECT product_id, SUM(quantity) FROM prod_in_warehouse group by product_id;", nativeQuery = true)
    public Map<Integer, Integer> totalProd();

    @Query(value = "SELECT product_id, SUM(quantity) FROM prod_in_warehouse group by product_id;", nativeQuery = true)
    public List<String> listProd();


}


