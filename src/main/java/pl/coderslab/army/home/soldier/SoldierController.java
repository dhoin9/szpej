package pl.coderslab.army.home.soldier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.warehouse.Warehouse;
import pl.coderslab.army.home.warehouse.WarehouseService;

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
    @ModelAttribute("roles")
    public List<Role> allRole() {
        return roleRepository.findAll();
    }


    @ResponseBody
    @RequestMapping(value = "/{id}")
    public String getBook(@PathVariable long id) {
        Soldier soldier = soldierService.get(id);
        return soldier.toString();
    }


        @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addForm(Soldier soldier){
        System.out.println(soldier);
        soldierService.add(soldier);
        return "redirect:/admin/soldier";}

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("soldier", new Soldier());
        return "admin/newSoldier";
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allBooks(Model model){
        model.addAttribute("soldiers", soldierService.getSoldiers());
        return "admin/allSoldier";
    }
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
