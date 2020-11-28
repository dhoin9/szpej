package pl.coderslab.army.home.order;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
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

    @Override
    public List<String> getTotal() {
        return repository.findOrderTotal();
    }

    public List<OrderTotal> getOrderTotal(){
        List<OrderTotal> totalList = new ArrayList<>();
        List<String> stringList = repository.findOrderTotal();
        for (String str: stringList){
            String[] strSplit=str.split(",");
            OrderTotal orderTotal = new OrderTotal(strSplit[0],strSplit[1], strSplit[2], Integer.parseInt(strSplit[3]));
            totalList.add(orderTotal);
        }return totalList;
    }
}




