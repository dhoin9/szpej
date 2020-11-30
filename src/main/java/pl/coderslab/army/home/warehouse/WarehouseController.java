package pl.coderslab.army.home.warehouse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.order.OrderService;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/warehouse")
public class WarehouseController {

    private final OrderService orderService;
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final ProdInWarehouseService prodInWarehouseService;

    public WarehouseController(OrderService orderService, ProductService productService, WarehouseService warehouseService, ProdInWarehouseService prodInWarehouseService) {
        this.orderService = orderService;
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.prodInWarehouseService = prodInWarehouseService;
    }

    @ModelAttribute("NameSize")
    public Map<String, List<Product>> getMapNameProduct() {
        return productService.getMapNameProduct();
    }


    @GetMapping("/new")
    public String addOrder(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        return "warehouse/new";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrder(Warehouse warehouse) {
        warehouseService.add(warehouse);
        return "redirect:/admin/warehouse" ;}

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allWarehouses(Model model){
        model.addAttribute("warehouses", warehouseService.getWarehouses());
        return "warehouse/allWarehouses";
    }
    @RequestMapping(value = "/stock/all", method = RequestMethod.GET)
    public String getProdInAll(Model model){
        model.addAttribute("prodIn", prodInWarehouseService.getProdInWarehousesTotal());
        return "warehouse/list";
    }
    @RequestMapping(value = "/stock/{id}", method = RequestMethod.GET)
    public String getProdIn(@PathVariable long id, Model model){
        Warehouse warehouse= warehouseService.get(id);
        model.addAttribute("prodIn", prodInWarehouseService.getProdInWarehouses(warehouse));
        return "warehouse/details";
    }
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String getOrders(@PathVariable long id, Model model){
        Warehouse warehouse= warehouseService.get(id);
        model.addAttribute("orders", orderService.getOrderByWarehouse(warehouse));
        return "warehouse/details";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String details(@PathVariable long id, Model model){
        Warehouse warehouse= warehouseService.get(id);
        model.addAttribute("orders", orderService.getOrderByWarehouse(warehouse));
        model.addAttribute("prodIn", prodInWarehouseService.getProdInWarehouses(warehouse));
        return "warehouse/details";
    }

}
