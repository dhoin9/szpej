package pl.coderslab.army.home.warehouse;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.army.home.order.OrderRepository;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseRepository;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class JpaWarehouseService implements WarehouseService {

    private final WarehouseRepository repository;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ProdInWarehouseRepository prodInRepo;


    public JpaWarehouseService(WarehouseRepository repository, ProductService productService, OrderRepository orderRepository, ProdInWarehouseRepository prodInRepo) {
        this.repository = repository;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.prodInRepo = prodInRepo;
    }


    @Override
    public Warehouse get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Warehouse> getWarehouses() {
        return repository.findAll();
    }

    @Override
    public void add(Warehouse warehouse) {
        repository.save(warehouse);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void update(Warehouse warehouse) {
        repository.save(warehouse);

    }

    public Map<Product,Integer> countOrder(Warehouse warehouse){
        Map<Product,Integer> map = new HashMap<>();
        for (Product product: productService.getProducts()){
            map.put(product, orderRepository.countAllByProduct(product) );
        } return map;
    }

    public Map<Product,Integer> countStock(Warehouse warehouse){
        Map<Product,Integer> map = new HashMap<>();
        for (Product product: productService.getProducts()){
            map.put(product, orderRepository.countAllByProduct(product) );
        } return map;
    }
}




