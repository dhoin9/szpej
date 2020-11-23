package pl.coderslab.army.home.order;

import java.util.List;

public interface OrderService {
    public Order get(Long id);

    public List<Order> getOrders();

    public void add(Order order);

    public void delete(Long id);

    public void update(Order order);
}
