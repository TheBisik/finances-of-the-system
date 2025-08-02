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
        String name  = user.getAttribute("name");      // lub "given_name"/"family_name"
        String picture   = user.getAttribute("picture");

    //zapisuje do modelu thyaleaf dla pliku html potrzebne dane do wyswietlenia
        model.addAttribute("sub", sub);

        //Sprawdzam czy użytkownik ma dostep do systemu, pytam serwis który pytta dane czy istnieje taki uzytkownik pod takim googleid(sub) <- inaczej sub
        boolean exists = appUserService.userExists(sub);
        
        if (exists) {
            return "redirect:/dashboard"; //przekierowanie na inny endpoint
        } else {
            model.addAttribute("error", "Użytkownik o ID “" + sub + "” nie istnieje.");
            return "check-id"; //przekazuje do folderu a nastepnie pliku resources/check-id
        }



    }

}
