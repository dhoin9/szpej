package pl.coderslab.army.home.EquipmentPass;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.army.home.ProdInWarehouse.ProdInWarehouse;
import pl.coderslab.army.home.ProdInWarehouse.ProdInWarehouseService;
import pl.coderslab.army.home.products.ProductService;

import java.util.List;

@Controller
@RequestMapping("/equipment/")
public class EquipmentPassController {

    private final EquipmentPassService equipmentPassService;
    private final ProdInWarehouseService prodInWarehouseService;
    private final ProductService productService;

    public EquipmentPassController(EquipmentPassService equipmentPassService, ProdInWarehouseService prodInWarehouseService, ProductService productService) {
        this.equipmentPassService = equipmentPassService;
        this.prodInWarehouseService = prodInWarehouseService;
        this.productService = productService;
    }

    //    @ModelAttribute("products")
//    public List<String> getProductsNames(){
//        return productService.getProductsName();
//    }
    @ModelAttribute("productsWarehouse")
    public List<ProdInWarehouse> getProduct(){
        return prodInWarehouseService.getProdInWarehouses();}

    @GetMapping("/new")
    public String addEquipment(Model model) {

        model.addAttribute("equipment", new EquipmentPass());
        return "equipment/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addEquipment(EquipmentPass equipment){
        ProdInWarehouse prodInWarehouse = prodInWarehouseService.get(equipment.getProduct(), equipment.getWarehouse());
        prodInWarehouse.setQuantity(prodInWarehouse.getQuantity()-equipment.getQuantity());
        return "redirect:/equipment/all";
    }

}
