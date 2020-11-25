package pl.coderslab.army.home.products;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public Product get(Long id);

    public List<Product> getProducts();
    public List<String> getProductsName();
    public List<String> getProductSize(String name);

    public Map<String,List<String>> getMapNameSize();

    public Map<String,List<Product>> getMapNameProduct();

    public void add(Product product);

    public void delete(Long id);

    public void update(Product product);
}
