package pl.coderslab.army.home.order;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private List<Order> orderList;


    public OrderList(){
        this.orderList = new ArrayList<Order>();
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    public void add(Order order){
        this.orderList.add(order);
    }
}
