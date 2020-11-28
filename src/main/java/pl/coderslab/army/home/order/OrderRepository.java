package pl.coderslab.army.home.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {




    @Query(value ="SELECT p.name, p.size,  w.name as unit ,  COUNT(*) from orders JOIN soldier on orders.soldier_id = soldier.id\n" +
            "    JOIN warehouse w on soldier.warehouse_id = w.id\n" +
            "    JOIN product p on p.id = orders.product_id\n" +
            "    GROUP BY product_id, warehouse_id;", nativeQuery = true)
    public List<String> findOrderTotal();
}


