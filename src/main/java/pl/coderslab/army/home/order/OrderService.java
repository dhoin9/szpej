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
    public void add(Order order);
    public void delete(Long id);
    public void update(Order order);
    public List<String> getTotal();
    public List<OrderTotal> getOrderTotal();
    public List<OrderTotal> getOrderByWarehouse(Warehouse warehouse);
    public Map<Product, Integer> mapOrders();

}
