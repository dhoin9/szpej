package pl.coderslab.army.home.soldier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.warehouse.Warehouse;
import pl.coderslab.army.home.warehouse.WarehouseService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/soldier")
public class SoldierController {

    private final SoldierService soldierService;
    private final WarehouseService warehouseService;
    private final RoleRepository roleRepository;

    public SoldierController(SoldierService soldierService, WarehouseService warehouseService, RoleRepository roleRepository) {
        this.soldierService = soldierService;
        this.warehouseService = warehouseService;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("warehouses")
    public List<Warehouse> allWarehouses() {
        return warehouseService.getWarehouses();
    }
    @ModelAttribute("roleList")
    public List<Role> allRole() {
        return roleRepository.findAll();
    }


    @ResponseBody
    @RequestMapping(value = "/{id}")
    public String getSoldier(@PathVariable long id) {
        Soldier soldier = soldierService.get(id);
        return soldier.toString();
    }
        @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addSoldier(@Valid @ModelAttribute("soldier") Soldier soldier,  BindingResult result, Model model){
        List<Soldier> soldiers = soldierService.getSoldiers();
        for(Soldier sold:soldiers){
            if(soldier.getEmail().equals(sold.getEmail())){
                model.addAttribute("error", "already exist");
                return "admin/newSoldier";
            }
        }
        if(result.hasErrors()){
            return "admin/newSoldier";
        }else{
        soldierService.add(soldier);
        return "redirect:/admin/soldier";}
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addSoldier(Model model) {
        model.addAttribute("soldier", new Soldier());
        return "admin/newSoldier";
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allSoldier(Model model){
        model.addAttribute("soldiers", soldierService.getSoldiers());
        return "admin/allSoldier";
    }
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editSoldier(@PathVariable long id, Model model) {
        model.addAttribute("soldier", soldierService.get(id));
        return "admin/editSoldier";}

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public  String editSoldier(Soldier soldier) {
        soldierService.update(soldier);
        return "redirect:/admin/soldier";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSoldier(@PathVariable long id) {
        soldierService.delete(id);
        return "redirect:/admin/soldier";
    }
}
