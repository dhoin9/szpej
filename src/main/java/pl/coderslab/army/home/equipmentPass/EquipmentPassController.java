package pl.coderslab.army.home.equipmentPass;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.order.Order;
import pl.coderslab.army.home.order.OrderService;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouse;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;
import pl.coderslab.army.home.soldier.CurrentUser;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.soldier.SoldierService;
import pl.coderslab.army.home.warehouse.WarehouseService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/equipment")
public class EquipmentPassController {

    private final EquipmentPassService equipmentPassService;
    private final ProdInWarehouseService prodInWarehouseService;
    private final ProductService productService;
    private final SoldierService soldierService;
    private final WarehouseService warehouseService;
    private final OrderService orderService;


    public EquipmentPassController(EquipmentPassService equipmentPassService, ProdInWarehouseService prodInWarehouseService, ProductService productService, SoldierService soldierService, WarehouseService warehouseService, OrderService orderService) {
        this.equipmentPassService = equipmentPassService;
        this.prodInWarehouseService = prodInWarehouseService;
        this.productService = productService;
        this.soldierService = soldierService;
        this.warehouseService = warehouseService;
        this.orderService = orderService;
    }

    @ModelAttribute("products")
    public List<Product> getProduct() {
        return productService.getProducts();
    }

    @ModelAttribute("NameSize")
    public Map<String, List<Product>> getMapNameProduct() {
        return productService.getMapNameProduct();
    }
    @ModelAttribute("currentSoldier")
    public Soldier sortedProduct(@AuthenticationPrincipal CurrentUser customUser){
        return  customUser.getSoldier();
    }
    @ModelAttribute("productsWarehouse")
    public List<ProdInWarehouse> getProductInWare() {
        return prodInWarehouseService.getProdInWarehouses();
    }

    @ModelAttribute("soldiers")
    public List<Soldier> getSoldiers() {
        return soldierService.getSoldiers();
    }

    @GetMapping("/{id}/new")
    public String addEquipment(@PathVariable long id, Model model) {
        model.addAttribute("equipmentPasses", new EquipmentPassList());
        model.addAttribute("soldier", soldierService.get(id));
        return "admin/newEquipment";
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.POST)
    public String addEquipment( EquipmentPassList equipmentPasseslist) {
        Soldier soldier = equipmentPasseslist.getEquipmentPassList().get(0).getSoldier();
        for (EquipmentPass equipment : equipmentPasseslist.getEquipmentPassList()) {
            System.out.println("Product" + equipment.getProduct());//for development purpose
            if (equipment.getQuantity() > 0) {
                List<Order> orders= orderService.getOrdersBySoldierAndProduct(soldier,equipment.getProduct());
                orderService.setInactiveOrders(orders);
                ProdInWarehouse prodInWarehouse = prodInWarehouseService.get(equipment.getProduct(), equipment.getWarehouse());
                prodInWarehouse.setQuantity(prodInWarehouse.getQuantity() - equipment.getQuantity());
                prodInWarehouseService.update(prodInWarehouse);
                equipmentPassService.add(equipment);
            }
        }
        return "redirect:";

    }


    @RequestMapping(value = "/{id}")
    public String getEquipment(@PathVariable long id, Model model) {
        Soldier soldier = soldierService.get(id);
        List<EquipmentPass> equipmentPass = equipmentPassService.getEquipmentPasses(soldier);
        model.addAttribute("equipmentPass", equipmentPass);
        model.addAttribute("soldier", soldier);
        return "admin/soldierEquipment";
    }

    @RequestMapping(value = "/{id}/delete/{idd}", method = RequestMethod.GET)
    public String deleteEquipment(@PathVariable long idd, @PathVariable long id) {
        equipmentPassService.delete(idd);
        return "redirect:/admin/equipment/{id}";
    }
}
