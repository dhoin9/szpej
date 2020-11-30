package pl.coderslab.army.home.order;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class JpaOrderService implements OrderService {

    private final OrderRepository repository;
    private final ProductService productService;

    public JpaOrderService(OrderRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public Order get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public List<Order> getOrdersBySoldier(Soldier soldier) {
        return repository.findOrdersBySoldier(soldier);
    }

    @Override
    public void add(Order order) {
        repository.save(order);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Order order) {
        repository.save(order);

    }

    @Override
    public List<String> getTotal() {
        return repository.findOrderTotal();
    }

    public List<OrderTotal> getOrderTotal() {
        List<OrderTotal> totalList = new ArrayList<>();
        List<String> stringList = repository.findOrderTotal();
        for (String str : stringList) {
            String[] strSplit = str.split(",");
            OrderTotal orderTotal = new OrderTotal(strSplit[0], strSplit[1], strSplit[2], Integer.parseInt(strSplit[3]));
            totalList.add(orderTotal);
        }
        return totalList;
    }

    @Override
    public List<OrderTotal> getOrderByWarehouse(Warehouse warehouse) {
        List<OrderTotal> totalList = new ArrayList<>();
        List<String> stringList = repository.findOrderByWarehouse(warehouse.getId());
        for (String str : stringList) {
            String[] strSplit = str.split(",");
            OrderTotal orderTotal = new OrderTotal(strSplit[0], strSplit[1], strSplit[2], Integer.parseInt(strSplit[3]));
            totalList.add(orderTotal);
        }
            return totalList;
    }

    @Override
    public Map<Product, Integer> mapOrders() {
        Map<Product, Integer> map = new HashMap<>();
        List<String> products= repository.findProducts();
        for(String str:products){
            Product product = productService.get(Long.valueOf(str));
            map.put(product, repository.countAllByProduct(product));
        }
        return map;
    }
}




