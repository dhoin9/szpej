package pl.coderslab.army.home.soldier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home/soldier")
public class SoldierController {

    private final SoldierService soldierService;

    public SoldierController(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    @ResponseBody
    @RequestMapping("/home/soldier")
    public String addSoldier() {
        return "home";
    }
    @ResponseBody
    @RequestMapping(value="/{id}")
    public String getBook(@PathVariable long id) {
       Soldier soldier =  soldierService.get(id);
        return soldier.toString();}
}
