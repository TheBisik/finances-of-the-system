package pl.pcdevs.systemfinansowy.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pcdevs.systemfinansowy.service.AppUserService;


@Controller
public class AccesController {

    private final AppUserService appUserService;

    public AccesController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/check-id")
    public String checkAcces(Model model, OAuth2AuthenticationToken authentication) { //rozszerzam o model i oAth2 google
        OAuth2User user = authentication.getPrincipal(); //pobieram z oauth2 springa aktualnie zalogowanego użtykownika
        String sub = user.getAttribute("sub"); // pobieram id sub z niego

        boolean exists = appUserService.userExists(sub);

        if (exists) {
            return "/auth/dashboard";
        } else {
            model.addAttribute("sub", sub); // dodaje sub do modelu który potem zwracam do formularza
            return "check-id";
        }



    }

}
