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
    public List<Order> getOrdersBySoldierAndProduct(Soldier soldier, Product product) {
        return repository.findOrdersBySoldierAndProduct(soldier, product);
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
    public void deleteList(List<Order> orders) {
    for(Order order: orders){
        repository.delete(order);
    }
    }

    @Override
    public void update(Order order) {
        repository.save(order);

    }

    @Override
    public void setInactiveOrders(List<Order> orders) {
        for (Order order : orders) {
            order.setActive(false);
            update(order);
        }
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
    public List<Order> ordersByWarehouse(Warehouse warehouse) {
        List<Order> orders = new ArrayList<>();
        List<String> orderString = repository.allOrdersByWarehouse(warehouse.getId());
        for (String str : orderString) {
            orders.add(repository.getOne(Long.valueOf(str)));
        }
        return orders;
    }

    @Override
    public Map<Product, Integer> mapOrders() {
        Map<Product, Integer> map = new HashMap<>();
        for (Product product : productService.getProducts()) {
            try {
                if (repository.sumByProduct(product.getId()).equals("")) {
                    map.put(product, 0);
                } else {
                    map.put(product, Integer.valueOf(repository.sumByProduct(product.getId())));
                }
            } catch (NullPointerException e) {
                System.out.println(product + " is empty");
                map.put(product, 0);
            }
        }
        return map;
    }

    public Map<Product, Integer> mapOrders(Warehouse warehouse) {
        Map<Product, Integer> map = new HashMap<>();

        for (Product product : productService.getProducts()) {
            try {
                if (repository.sumByWarehouseAndProduct(warehouse.getId(), product.getId()).equals("")) {
                    map.put(product, 0);
                } else {
                    map.put(product, Integer.valueOf(repository.sumByWarehouseAndProduct(warehouse.getId(), product.getId())));
                }
            } catch (NullPointerException e) {
                System.out.println(product + " is empty");
                map.put(product, 0);
            }
        }
        return map;
    }

}




