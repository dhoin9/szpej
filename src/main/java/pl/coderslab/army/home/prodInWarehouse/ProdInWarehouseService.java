package pl.coderslab.army.home.prodInWarehouse;

import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.List;
import java.util.Map;

public interface ProdInWarehouseService {
    public ProdInWarehouse get(Long id);

    public ProdInWarehouse get(Product product, Warehouse warehouse);

    public List<ProdInWarehouse> getProdInWarehouses();
    public List<ProdInWarehouse> getProdInWarehouses(Warehouse warehouse);
    public Map<Product, Integer> getProdInWarehousesTotal();
    public Map<Product, Integer> getProdInWarehousesTotal(Warehouse warehouse);
    public void add(ProdInWarehouse prodInWarehouse);
    public void delete(Long id);

    public void update(ProdInWarehouse prodInWarehouse);
}
