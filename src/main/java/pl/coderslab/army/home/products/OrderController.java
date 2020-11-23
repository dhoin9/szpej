package pl.coderslab.army.home.products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.army.home.order.OrderService;

@Controller
@RequestMapping("/soldier/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/home/soldier")
    public String addSoldier() {
        return "orderForm";
    }

}
