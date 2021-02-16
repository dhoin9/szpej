package pl.coderslab.army.home.blood;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.army.home.soldier.CurrentUser;
import pl.coderslab.army.home.soldier.Soldier;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/admin/donator")
public class DonatorController {
    private final DonationService donationService;
    private final DonatorService donatorService;

    public DonatorController(DonationService donationService, DonatorService donatorService) {
        this.donationService = donationService;
        this.donatorService = donatorService;
    }

    @ModelAttribute("donators")
    public List<Donator> getDonators() {
        return donatorService.getAll();
    }

    @ModelAttribute("soldier")
    public Soldier soldier(@AuthenticationPrincipal CurrentUser customUser){
        return customUser.getSoldier();
    }
    @GetMapping("")
    public List<Donator> allDonators(){
        return donatorService.getAll();
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model){
        Donator donator = donatorService.get(id);
        model.addAttribute("donations", donationService.donationBySoldier(donator));
        model.addAttribute("donator", donator);
        return "admin/donator";
    }

    @PostMapping("/{idp}/new")
    public String addDonation(@PathVariable long idp, Donation donation){
        donation.setDonator( donatorService.get(idp));
        donationService.add(donation);
        return "redirect:/admin/donator/"+idp;
    }
    @GetMapping("/{idp}/new")
    public String addDonation( Model model){
//        model.addAttribute("donator", donatorService.get(id));
        model.addAttribute("donation", new Donation());
     return "admin/newDonation";
    }

    @GetMapping("/unit/{id}")
    public List<Donator> allOrders(@PathVariable long id){
        return donatorService.donorByUnit(id);
    }
}
