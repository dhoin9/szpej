package pl.coderslab.army.home.soldier;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.army.exceptions.SoldierNotFound;
import pl.coderslab.army.home.warehouse.Warehouse;
import pl.coderslab.army.home.warehouse.WarehouseService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/soldier")
public class SoldierController {

    private final SoldierService soldierService;
    private final WarehouseService warehouseService;
    private final RoleRepository roleRepository;
    private final SoldierRepository soldierRepository;

    public SoldierController(SoldierService soldierService, WarehouseService warehouseService, RoleRepository roleRepository, SoldierRepository soldierRepository) {
        this.soldierService = soldierService;
        this.warehouseService = warehouseService;
        this.roleRepository = roleRepository;
        this.soldierRepository = soldierRepository;
    }

    @ModelAttribute("warehouses")
    public List<Warehouse> allWarehouses() {
        return warehouseService.getWarehouses();
    }

    @ModelAttribute("roleList")
    public List<Role> allRole() {
        return roleRepository.findAll();
    }
    @ModelAttribute("currentSoldier")
    public Soldier sortedProduct(@AuthenticationPrincipal CurrentUser customUser){
        return  customUser.getSoldier();
    }
    @ResponseBody
    @RequestMapping(value = "/{id}")
    public String getSoldier(@PathVariable long id) {
        Soldier soldier = soldierService.get(id);

        try {
            soldier.toString();
        } catch (SoldierNotFound s) {
            s.SoldierNotFound();
            System.out.println("nie ma kolegi");

        } catch (EntityNotFoundException e) {
            System.out.println("entity błąd");
            new RedirectView("/404");
            e.getMessage();
            return "nie ma kolegi";

        }

        return soldier.toString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addSoldier(@Valid @ModelAttribute("soldier") Soldier soldier, BindingResult result, Model model) {
        if (soldierService.getSoldier(soldier.getEmail()) != null) {
            model.addAttribute("error", "already exist");
            return "admin/newSoldier";
        }
        if (result.hasErrors()) {
            return "admin/newSoldier";
        } else {
            System.out.println(soldier.toString());
            soldierService.add(soldier);
            return "redirect:/admin/soldier";
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addSoldier(Model model) {
        model.addAttribute("soldier", new Soldier());
        return "admin/newSoldier";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allSoldier(Model model) {
        model.addAttribute("soldiers", soldierService.getSoldiers());
        return "admin/allSoldier";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editSoldier(@PathVariable String id, Model model) {
        try{
            Long idl = Long.parseLong(id);
        Soldier soldier = soldierService.get(idl);
        model.addAttribute("soldier", soldierService.get(idl));


            soldier.toString();

        } catch (Exception s) {
            model.addAttribute("message", "Soldier not exist");
            return "redirect:/404";
        }
        return "admin/editSoldier";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editSoldier(Soldier soldier) {
        try{
        soldierService.update(soldier);
        }catch (Exception e){
            return "redirect:/404";
        }

        return "redirect:/admin/soldier";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSoldier(@PathVariable long id) {
        try{
            soldierService.delete(id);
        }catch (Exception e){
            return "redirect:/404";
        }
        return "redirect:/admin/soldier";
    }
}
