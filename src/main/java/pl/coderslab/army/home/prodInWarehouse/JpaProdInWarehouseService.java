package pl.coderslab.army.home.prodInWarehouse;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductRepository;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class JpaProdInWarehouseService implements ProdInWarehouseService {

    private final ProdInWarehouseRepository repository;
    private final ProductRepository productRepository;

    public JpaProdInWarehouseService(ProdInWarehouseRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }


    @Override
    public ProdInWarehouse get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public ProdInWarehouse get(Product product, Warehouse warehouse) {
        return repository.findByProductAndWarehouse(product, warehouse);
    }

    @Override
    public List<ProdInWarehouse> getProdInWarehouses() {
        return repository.findAll();
    }

    @Override
    public List<ProdInWarehouse> getProdInWarehouses(Warehouse warehouse) {
        return repository.findAllByWarehouse(warehouse);
    }

    @Override
    public Map<Product, Integer> getProdInWarehousesTotal() {
        Map<Product, Integer> map= new HashMap<>();
        for (Product product : productRepository.findAll()) {
            try {
                if(repository.findAllByProduct(product)!=null)
                {int quantity = 0;
                    for(ProdInWarehouse prod:repository.findAllByProduct(product)){
                        quantity+=prod.getQuantity();
                    }
                    map.put(product, quantity);
                } else {
                    map.put(product, 0);
                }
            } catch (NullPointerException e) {
                System.out.println(product + " is empty");
                map.put(product, 0);
            }
        }
        return map ;
    }

    @Override
    public Map<Product, Integer> getProdInWarehousesTotal(Warehouse warehouse) {
        Map<Product, Integer> map = new HashMap<>();
        for (Product product : productRepository.findAll()) {
            try {
                if(repository.findByProductAndWarehouse(product, warehouse)!=null)
                {
                    map.put(product, repository.findByProductAndWarehouse(product, warehouse).getQuantity());
                } else {
                    map.put(product, 0);
                }
            } catch (NullPointerException e) {
                System.out.println(product + " is empty");
                map.put(product, 0);
            }
        }
        return map;

    }



    @Override
    public void add(ProdInWarehouse prodInWarehouse) {
        repository.save(prodInWarehouse);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void update(ProdInWarehouse prodInWarehouse) {
        repository.save(prodInWarehouse);

    }
}




