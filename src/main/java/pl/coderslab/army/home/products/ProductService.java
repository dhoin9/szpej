package pl.coderslab.army.home.products;

import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public Product get(Long id);

    public List<Product> getProducts();
    public List<String> getProductsName();
    public List<String> getProductSize(String name);

    public Map<String,List<String>> getMapNameSize();

    public Map<String,List<Product>> getMapNameProduct();
    public Map<Warehouse,Integer> getProductWarehouseOrder(Product product);
    public Map<Warehouse, Integer> getProductWarehouseStock(Product product);

    public void add(Product product);

    public void delete(Long id);

    public void update(Product product);
}
