package pl.coderslab.army.home.products;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Primary
@Transactional
public class JpaProductService implements ProductService {

    private final ProductRepository repository;

    public JpaProductService(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public Product get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public List<String> getProductsName() {
        return repository.findAllUniqueName();
    }

    @Override
    public void add(Product product) {
        repository.save(product);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void update(Product product) {
        repository.save(product);

    }
}




