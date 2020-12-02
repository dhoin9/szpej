package pl.coderslab.army.home.products;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.army.home.order.OrderRepository;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseRepository;
import pl.coderslab.army.home.warehouse.Warehouse;
import pl.coderslab.army.home.warehouse.WarehouseRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
@Transactional
public class JpaProductService implements ProductService {

    private final ProductRepository repository;
    private final OrderRepository orderRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProdInWarehouseRepository prodInWarehouseRepository;

    public JpaProductService(ProductRepository repository, OrderRepository orderRepository, WarehouseRepository warehouseRepository, ProdInWarehouseRepository prodInWarehouseRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.warehouseRepository = warehouseRepository;
        this.prodInWarehouseRepository = prodInWarehouseRepository;
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
        List<String> nameList = repository.findAllUniqueName();
        Map<String, List<String>> map = new HashMap<>();
        for (String name : nameList) {
            map.put(name, repository.findAllSizeByName(name));
        }
        return map;
    }

    @Override
    public Map<String, List<Product>> getMapNameProduct() {
        List<String> nameList = repository.findAllUniqueName();
        Map<String, List<Product>> map = new HashMap<>();
        for (String name : nameList) {
            map.put(name, repository.findAllByNameOrderBySize(name));
        }
        return map;
    }

    @Override
    public Map<Warehouse, Integer> getProductWarehouseOrder(Product product) {
        Map<Warehouse, Integer> map = new HashMap<>();
        for (Warehouse warehouse : warehouseRepository.findAll()) {
            try {
                if (orderRepository.sumByWarehouseAndProduct(warehouse.getId(), product.getId()).equals("")) {
                    System.out.println("Pusto tutaj");
                    map.put(warehouse, 999);
                } else {
                    map.put(warehouse, Integer.valueOf(orderRepository.sumByWarehouseAndProduct(warehouse.getId(), product.getId())));
                }
            } catch (NullPointerException e) {
                System.out.println(product + " is empty");
                map.put(warehouse, 0);
            }
        }
        return map;
    }

    @Override
    public Map<Warehouse, Integer> getProductWarehouseStock(Product product) {
        Map<Warehouse, Integer> map = new HashMap<>();
        for (Warehouse warehouse : warehouseRepository.findAll()) {
            try {
                if (prodInWarehouseRepository.findByProductAndWarehouse(product, warehouse) != null) {
                    map.put(warehouse, prodInWarehouseRepository.findByProductAndWarehouse(product, warehouse).getQuantity());
                } else {
                    map.put(warehouse, 0);
                }
            } catch (NullPointerException e) {
                System.out.println(product + " is empty");
                map.put(warehouse, 0);
            }
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




