package pl.coderslab.army.home.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.order.OrderService;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.soldier.SoldierService;
import pl.coderslab.army.home.warehouse.Warehouse;
import pl.coderslab.army.home.warehouse.WarehouseRepository;
import pl.coderslab.army.home.warehouse.WarehouseService;

import java.util.List;

@Controller
@RequestMapping("admin/product")
public class ProductController {

    private final SoldierService soldierService;
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProdInWarehouseService prodInWarehouseService;
    private final OrderService orderService;
    private final WarehouseRepository warehouseRepository;


    public ProductController(SoldierService soldierService, WarehouseService warehouseService, ProductService productService, ProductRepository productRepository, ProdInWarehouseService prodInWarehouseService, OrderService orderService, WarehouseRepository warehouseRepository) {
        this.soldierService = soldierService;
        this.warehouseService = warehouseService;
        this.productService = productService;
        this.productRepository = productRepository;
        this.prodInWarehouseService = prodInWarehouseService;
        this.orderService = orderService;
        this.warehouseRepository = warehouseRepository;
    }

    @ModelAttribute("warehouses")
    public List<Warehouse> allWarehouses() {
        return warehouseRepository.getAllOrderByName();
    }
    @ModelAttribute("products")
    public List<Product> sortedProduct(){
        return productRepository.getProductsOrderByName();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allProducts( Model model){
        model.addAttribute("prodIn", prodInWarehouseService.getProdInWarehousesTotal());
        model.addAttribute("orders", orderService.mapOrders());
        return "admin/allProducts";}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String allProducts(@PathVariable long id, Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);
        model.addAttribute("orders", productService.getProductWarehouseOrder(product));
        model.addAttribute("prodIn", productService.getProductWarehouseStock(product));
        return "admin/detailsProduct";}


        @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addForm(Soldier soldier){
        System.out.println(soldier);
        soldierService.add(soldier);
        return "redirect:/soldier/all";}

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("soldier", new Soldier());
        return "admin/newSoldier";
    }
//
//    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
//    public String getBook(@PathVariable long id, Model model) {
//        model.addAttribute("book", bookDao.findById(id));
//        return "book/edit";}
//
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
//    public RedirectView editBook(Book book) {
//        bookDao.update(book);
//        return new RedirectView("/bookform/all");
//    }
//
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSoldier(@PathVariable long id) {
        soldierService.delete(id);
        return "redirect:/";
    }
}
