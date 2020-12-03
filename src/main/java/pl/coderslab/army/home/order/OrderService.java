package pl.coderslab.army.home.order;

import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public Order get(Long id);

    public List<Order> getOrders();
    public List<Order> getOrdersBySoldier(Soldier soldier);
    public List<Order> getOrdersBySoldierAndProduct(Soldier soldier, Product product);
    public void add(Order order);
    public void delete(Long id);
    public void deleteList(List<Order> orders);
    public void update(Order order);
    public void setInactiveOrders(List<Order> orders);
    public List<String> getTotal();
    public List<OrderTotal> getOrderTotal();
    public List<OrderTotal> getOrderByWarehouse(Warehouse warehouse);
    public List<Order> ordersByWarehouse(Warehouse warehouse);
    public Map<Product, Integer> mapOrders();
    public Map<Product, Integer> mapOrders(Warehouse warehouse);
}
