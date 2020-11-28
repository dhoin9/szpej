package pl.coderslab.army.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.army.home.soldier.Soldier;
import pl.coderslab.army.home.users2.CurrentUser;

@Controller
@Slf4j
public class HomeController {

    @ModelAttribute("soldier")
    public Soldier getCurrent(@AuthenticationPrincipal CurrentUser customUser) {
        return customUser.getAppUser();
    }

    @RequestMapping("/home")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String home(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        Soldier soldier = customUser.getAppUser();
        model.addAttribute("soldier", soldier);
        return "soldier/index";
    }

    @RequestMapping("/")
    public String theme(){
        log.info("asdasd {}", 12);
        return "index";
    }


}
