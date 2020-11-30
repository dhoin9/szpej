package pl.coderslab.army.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.equipmentPass.EquipmentPassService;
import pl.coderslab.army.home.order.Order;
import pl.coderslab.army.home.order.OrderList;
import pl.coderslab.army.home.order.OrderService;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.users2.CurrentUser;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    private final EquipmentPassService equipmentPassService;
    private final OrderService orderService;
    private final ProductService productService;

    public HomeController(EquipmentPassService equipmentPassService, OrderService orderService, ProductService productService) {
        this.equipmentPassService = equipmentPassService;
        this.orderService = orderService;
        this.productService = productService;
    }
    @ModelAttribute("NameSize")
    public Map<String, List<Product>> getMapNameProduct() {
        return productService.getMapNameProduct();
    }

//    @ModelAttribute("soldier")
//    public Soldier getCurrent(@AuthenticationPrincipal CurrentUser customUser) {
//        return customUser.getAppUser();
//    }

    @RequestMapping("/home")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String home(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        Soldier soldier = customUser.getAppUser();
        model.addAttribute("soldier", soldier);
        model.addAttribute("equipments",equipmentPassService.getEquipmentPasses(soldier) );
        return "user/home";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String allOrders(@AuthenticationPrincipal CurrentUser customUser, Model model){
        Soldier soldier = customUser.getAppUser();
        model.addAttribute("soldier", soldier);
        model.addAttribute("orders", orderService.getOrdersBySoldier(soldier));
        return "user/orders";
    }
    @GetMapping("order/new")
    public String addOrder(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        Soldier soldier = customUser.getAppUser();
        model.addAttribute("orders", new OrderList());
        model.addAttribute("soldier", soldier);
        return "user/new";
    }

    @RequestMapping(value = "order/new", method = RequestMethod.POST)
    public String addOrder(OrderList orderList) {
        for (Order order : orderList.getOrderList()) {
            orderService.add(order); }
        return "redirect:/order" ;}


    @RequestMapping("/")
    public String theme(){
        log.info("asdasd {}", 12);
        return "index";
    }

    @RequestMapping(value="order/edit/{id}", method = RequestMethod.GET)
    public String editOrder( @PathVariable long id, Model model) {
        model.addAttribute("order", orderService.get(id));
        System.out.println( orderService.get(id));
        return "user/orderEdit";}

    @RequestMapping(value = "order/edit/{id}", method = RequestMethod.POST)
    public String editOrder(Order order) {
        System.out.println(order);
        orderService.update(order);
        return "redirect:/order" ;
    }

}
