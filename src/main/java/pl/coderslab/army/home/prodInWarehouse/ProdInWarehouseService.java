package pl.coderslab.army.home.prodInWarehouse;

import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.List;

public interface ProdInWarehouseService {
    public ProdInWarehouse get(Long id);

    public ProdInWarehouse get(Product product, Warehouse warehouse);

    public List<ProdInWarehouse> getProdInWarehouses();

    public void add(ProdInWarehouse prodInWarehouse);

    public void delete(Long id);

    public void update(ProdInWarehouse prodInWarehouse);
}
