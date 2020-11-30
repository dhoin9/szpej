package pl.coderslab.army.home.equipmentPass;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouse;
import pl.coderslab.army.home.prodInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.products.Product;
import pl.coderslab.army.home.products.ProductService;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.soldier.SoldierService;
import pl.coderslab.army.home.warehouse.WarehouseService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/equipment")
public class EquipmentPassController {

    private final EquipmentPassService equipmentPassService;
    private final ProdInWarehouseService prodInWarehouseService;
    private final ProductService productService;
    private final SoldierService soldierService;
    private final WarehouseService warehouseService;


    public EquipmentPassController(EquipmentPassService equipmentPassService, ProdInWarehouseService prodInWarehouseService, ProductService productService, SoldierService soldierService, WarehouseService warehouseService) {
        this.equipmentPassService = equipmentPassService;
        this.prodInWarehouseService = prodInWarehouseService;
        this.productService = productService;
        this.soldierService = soldierService;
        this.warehouseService = warehouseService;
    }

    @ModelAttribute("products")
    public List<Product> getProduct() {
        return productService.getProducts();
    }

    @ModelAttribute("NameSize")
    public Map<String, List<Product>> getMapNameProduct() {
        return productService.getMapNameProduct();
    }

    @ModelAttribute("productsWarehouse")
    public List<ProdInWarehouse> getProductInWare() {
        return prodInWarehouseService.getProdInWarehouses();
    }

    @ModelAttribute("soldiers")
    public List<Soldier> getSoldiers() {
        return soldierService.getSoldiers();
    }

    @GetMapping("/new")
    public String addEquipment(Model model) {
        model.addAttribute("equipmentPasses", new EquipmentPassList());
        model.addAttribute("soldier", new Soldier());
        return "equipment/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addEquipment(EquipmentPassList equipmentPasseslist) {
            Soldier soldier = equipmentPasseslist.getEquipmentPassList().get(0).getSoldier();
        for (EquipmentPass equipment : equipmentPasseslist.getEquipmentPassList()) {
            System.out.println("Product" + equipment.getProduct());//for development purpose
            ProdInWarehouse prodInWarehouse = prodInWarehouseService.get(equipment.getProduct(), equipment.getWarehouse());
            prodInWarehouse.setQuantity(prodInWarehouse.getQuantity() - equipment.getQuantity());
            prodInWarehouseService.update(prodInWarehouse);
            equipmentPassService.add(equipment);
        }
        return "redirect:/equipment/" +soldier.getId();

    }


    @RequestMapping(value = "/{id}")
    public String getEquipment(@PathVariable long id, Model model) {
        Soldier soldier = soldierService.get(id);
        List<EquipmentPass> equipmentPass = equipmentPassService.getEquipmentPasses(soldier);
        model.addAttribute("equipmentPass", equipmentPass);
        model.addAttribute("soldier", soldier);
        return "equipment/list";
    }

}
