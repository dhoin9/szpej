package pl.coderslab.army.home.products;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<String> getProductSize(String name) {
        return repository.findAllSizeByName(name);
    }

    @Override
    public Map<String, List<String>> getMapNameSize() {
        List<String> nameList= repository.findAllUniqueName();
        Map<String, List<String>> map = new HashMap<>();
        for(String name: nameList){
            map.put(name, repository.findAllSizeByName(name) );
        }
        return map;
    }

    @Override
    public Map<String, List<Product>> getMapNameProduct() {
        List<String> nameList= repository.findAllUniqueName();
        Map<String, List<Product>> map = new HashMap<>();
        for(String name: nameList){
            map.put(name, repository.findAllByNameOrderBySize(name) );
        }
        return map;
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




