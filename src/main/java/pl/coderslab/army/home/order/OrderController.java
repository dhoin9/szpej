package pl.coderslab.army.home.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;
import pl.coderslab.army.home.soldier.Soldier;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    //    @ModelAttribute("products")
//    public List<String> getProductsNames(){
//        return productService.getProductsName();
//    }
    @ModelAttribute("NameSize")
    public Map<String, List<Product>> getMapNameProduct() {
        return productService.getMapNameProduct();
    }


    @GetMapping("/new")
    public String addOrder(Model model) {
        model.addAttribute("orders", new OrderList());
        model.addAttribute("soldier", new Soldier());
        return "order/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addOrder(OrderList orderList) {
        for (Order order : orderList.getOrderList()) {
            orderService.add(order); }
        return "redirect:/order/all" ;}

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allBooks(Model model){
        model.addAttribute("orders", orderService.getOrders());
        return "order/list";
    }


}
