package pl.coderslab.army.home.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;

import java.util.List;

@Controller
@RequestMapping("/soldier/order")
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
    @ModelAttribute("products")
    public List<Product> getProduct(){
        return productService.getProducts();}

    @GetMapping("/new")
    public String addOrder(Model model) {
        model.addAttribute("prod", productService.getProductsName());
        model.addAttribute("order", new Order());
        return "orderForm";
    }

}
