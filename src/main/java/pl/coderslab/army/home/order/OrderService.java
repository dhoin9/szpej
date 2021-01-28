package pl.coderslab.army.home.order;

import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.warehouse.Warehouse;

import java.util.List;
import java.util.Map;

public interface OrderService {
     Order get(Long id);

     List<Order> getOrders();
     List<Order> getOrdersBySoldier(Soldier soldier);
     List<Order> getOrdersBySoldierAndProduct(Soldier soldier, Product product);
     void add(Order order);
     void delete(Long id);
     void deleteList(List<Order> orders);
     void update(Order order);
     void setInactiveOrders(List<Order> orders);
     List<String> getTotal();
     List<OrderTotal> getOrderTotal();
     List<OrderTotal> getOrderByWarehouse(Warehouse warehouse);
     List<Order> ordersByWarehouse(Warehouse warehouse);
     Map<Product, Integer> mapOrders();
     Map<Product, Integer> mapOrders(Warehouse warehouse);
}
