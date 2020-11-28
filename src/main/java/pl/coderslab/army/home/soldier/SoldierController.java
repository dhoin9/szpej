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

    public SoldierController(SoldierService soldierService, WarehouseService warehouseService) {
        this.soldierService = soldierService;
        this.warehouseService = warehouseService;
    }

    @ModelAttribute("warehouses")
    public List<Warehouse> allWarehouses() {
        return warehouseService.getWarehouses();
    }


    @ResponseBody
    @RequestMapping(value = "/{id}")
    public String getBook(@PathVariable long id) {
        Soldier soldier = soldierService.get(id);
        return soldier.toString();
    }


        @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addForm(Soldier soldier){
        System.out.println(soldier);
        soldierService.add(soldier);
        return "redirect:/soldier/all";}

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("soldier", new Soldier());
        return "soldier/new";
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allBooks(Model model){
        model.addAttribute("soldiers", soldierService.getSoldiers());
        return "soldier/table";
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
        return "redirect:/soldier/all";
    }
}
