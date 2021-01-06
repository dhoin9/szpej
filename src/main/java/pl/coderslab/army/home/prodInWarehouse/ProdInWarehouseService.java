package pl.coderslab.army.home.prodInWarehouse;

import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.List;
import java.util.Map;

public interface ProdInWarehouseService {
     ProdInWarehouse get(Long id);

     ProdInWarehouse get(Product product, Warehouse warehouse);

     List<ProdInWarehouse> getProdInWarehouses();
     List<ProdInWarehouse> getProdInWarehouses(Warehouse warehouse);
     Map<Product, Integer> getProdInWarehousesTotal();
     Map<Product, Integer> getProdInWarehousesTotal(Warehouse warehouse);
     void add(ProdInWarehouse prodInWarehouse);
     void delete(Long id);
     void update(ProdInWarehouse prodInWarehouse);
}
