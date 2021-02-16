package pl.coderslab.army.home;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.army.home.order.OrderService;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductRepository;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.soldier.CurrentUser;
import pl.coderslab.army.home.warehouse.Warehouse;
import pl.coderslab.army.home.warehouse.WarehouseService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final WarehouseService warehouseService;
    private final ProdInWarehouseService prodInWarehouseService;
    private final OrderService orderService;
    private final ProductRepository productRepository;

    public AdminController(WarehouseService warehouseService, ProdInWarehouseService prodInWarehouseService, OrderService orderService, ProductRepository productRepository) {
        this.warehouseService = warehouseService;
        this.prodInWarehouseService = prodInWarehouseService;
        this.orderService = orderService;
        this.productRepository = productRepository;
    }


    @ModelAttribute("products")
    public List<Product> sortedProduct(){
        return productRepository.getProductsOrderByName();
    }
    @ModelAttribute("soldier")
    public Soldier soldier(@AuthenticationPrincipal CurrentUser customUser){
        return customUser.getSoldier();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String details(@AuthenticationPrincipal CurrentUser customUser, Model model){
        Soldier soldier = customUser.getSoldier();
        Warehouse warehouse= soldier.getWarehouse();
        model.addAttribute("warehouse",warehouse);
        model.addAttribute("prodIn", prodInWarehouseService.getProdInWarehousesTotal(warehouse));
        model.addAttribute("orders", orderService.mapOrders(warehouse));
        return "admin/admin";
    }
}
