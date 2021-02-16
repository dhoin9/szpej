package pl.coderslab.army.home.warehouse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.army.home.order.OrderService;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouse;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductRepository;
import pl.coderslab.army.home.products.ProductService;
import pl.coderslab.army.home.soldier.CurrentUser;
import pl.coderslab.army.home.soldier.Soldier;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/warehouse")
public class WarehouseController {

    private final OrderService orderService;
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final ProdInWarehouseService prodInWarehouseService;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public WarehouseController(OrderService orderService, ProductService productService, WarehouseService warehouseService, ProdInWarehouseService prodInWarehouseService, ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.orderService = orderService;
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.prodInWarehouseService = prodInWarehouseService;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @ModelAttribute("NameSize")
    public Map<String, List<Product>> getMapNameProduct() {
        return productService.getMapNameProduct();
    }
    @ModelAttribute("products")
    public List<Product> sortedProduct(){
        return productRepository.getProductsOrderByName();
    }
    @ModelAttribute("soldier")
    public Soldier soldier(@AuthenticationPrincipal CurrentUser customUser){
        return customUser.getSoldier();
    }

//    @GetMapping("/new")
//    public String addOrder(Model model) {
//        model.addAttribute("warehouse", new Warehouse());
//        return "admin/new";
//    }
//
//    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    public String addOrder(Warehouse warehouse) {
//        warehouseService.add(warehouse);
//        return "redirect:/admin/warehouse" ;}

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allWarehouses(Model model){
        model.addAttribute("warehouses", warehouseRepository.getAllOrderByName());
        return "admin/allWarehouses";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String details(@PathVariable long id, Model model){
        Warehouse warehouse= warehouseService.get(id);
        model.addAttribute("warehouse",warehouse);
        model.addAttribute("prodIn", prodInWarehouseService.getProdInWarehousesTotal(warehouse));
        model.addAttribute("orders", orderService.mapOrders(warehouse));
        return "admin/detailsWarehouse";
    }

    @RequestMapping(value = "/{id}/{idp}", method = RequestMethod.GET)
    public String productsUpdate(@PathVariable long id, @PathVariable long idp, Model model){
        Warehouse warehouse= warehouseService.get(id);
        Product product=productService.get(idp);
        ProdInWarehouse prodInWarehouse = prodInWarehouseService.get(product,warehouse);
        if(prodInWarehouse==null){
            prodInWarehouse = new ProdInWarehouse(warehouse, product, 0);
            prodInWarehouseService.add(prodInWarehouse);
        }
        model.addAttribute("prodInWar", prodInWarehouse);
        return "admin/addProdWarehouse";
    }
    @RequestMapping(value = "/{id}/{idp}", method = RequestMethod.POST)
    public String productsUpdate(@PathVariable long id, ProdInWarehouse prodIn){
        prodInWarehouseService.update(prodIn);
        return "redirect:/admin/warehouse/"+id;
    }

}
