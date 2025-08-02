package pl.pcdevs.systemfinansowy.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2User user = authentication.getPrincipal();
        String sub = user.getAttribute("sub");
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");


        model.addAttribute("sub", sub);
        model.addAttribute("name", name);
        model.addAttribute("picture", picture);

        return "auth/dashboard";
    }

}


