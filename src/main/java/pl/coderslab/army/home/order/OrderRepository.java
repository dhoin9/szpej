package pl.coderslab.army.home.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.soldier.Soldier;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value ="SELECT p.name, p.size,  w.name as unit ,  COUNT(*) from orders JOIN soldier on orders.soldier_id = soldier.id\n" +
            "    JOIN warehouse w on soldier.warehouse_id = w.id\n" +
            "    JOIN product p on p.id = orders.product_id\n" +
            "WHERE active=true\n"+
            "    GROUP BY product_id, warehouse_id;", nativeQuery = true)
     List<String> findOrderTotal();

    @Query(value ="SELECT p.name, p.size,  w.name as unit ,  sum(quantity) from orders JOIN soldier on orders.soldier_id = soldier.id\n" +
            "    JOIN warehouse w on soldier.warehouse_id = w.id\n" +
            "    JOIN product p on p.id = orders.product_id\n" +
            "WHERE active=true ADN w.id=?1\n"+
            "    GROUP BY product_id, warehouse_id;", nativeQuery = true)
     List<String> findOrderByWarehouse(long warehouse);

     List<Order> findOrdersBySoldier(Soldier soldier);

    @Query(value ="SELECT orders.id from orders join soldier s on s.id = orders.soldier_id where s.warehouse_id=1? and active=true;",
            nativeQuery = true)
     List<String> allOrdersByWarehouse(long warehouse);

     int countAllByProduct(Product prod);

     List<Order> findOrdersBySoldierAndProduct(Soldier soldier, Product product);


    @Query(value ="SELECT sum(quantity) from orders join soldier s on s.id = orders.soldier_id \n" +
            "join warehouse w on w.id = s.warehouse_id WHERE s.warehouse_id=?1 and product_id=?2 and active=true", nativeQuery = true)
     String sumByWarehouseAndProduct(long warehouse, long product);

    @Query(value ="SELECT sum(quantity) from orders join soldier s on s.id = orders.soldier_id \n" +
            "join warehouse w on w.id = s.warehouse_id WHERE  product_id=?1 and active=true", nativeQuery = true)
     String sumByProduct(long product);


    @Query(value ="SELECT DISTINCT product_id from orders", nativeQuery = true)
     List<String> findProducts();


    @Query(value ="SELECT p.name, p.size,  w.name as unit ,  COUNT(*) from orders JOIN soldier on orders.soldier_id = soldier.id\n" +
            "    JOIN warehouse w on soldier.warehouse_id = w.id\n" +
            "    JOIN product p on p.id = orders.product_id\n" +
            "WHERE active=true ADN w.id=1?\n"+
            "    GROUP BY product_id, warehouse_id;", nativeQuery = true)
     List<String> orders(long warehouse);

}


