package pl.coderslab.army.home.order;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Primary
public class JpaOrderService implements OrderService {

    private final OrderRepository repository;

    public JpaOrderService(OrderRepository repository) {
        this.repository = repository;
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
}




