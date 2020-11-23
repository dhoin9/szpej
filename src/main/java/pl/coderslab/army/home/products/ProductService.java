package pl.coderslab.army.home.products;

import java.util.List;

public interface ProductService {
    public Product get(Long id);

    public List<Product> getProducts();

    public void add(Product product);

    public void delete(Long id);

    public void update(Product product);
}
