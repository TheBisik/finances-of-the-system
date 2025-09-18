package pl.pcdevs.systemfinansowy.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pcdevs.systemfinansowy.dto.UserProfileDto;
import pl.pcdevs.systemfinansowy.service.AppUserService;
import pl.pcdevs.systemfinansowy.service.mapper.UserProfileMapper;


@Controller
public class AccesController {

    private final AppUserService appUserService;

    private final UserProfileMapper userProfileMapper;

    public AccesController(AppUserService appUserService, UserProfileMapper userProfileMapper) {
        this.appUserService = appUserService;
        this.userProfileMapper = userProfileMapper;
    }

    @GetMapping("/check-id")
    public String checkAcces(Model model, OAuth2AuthenticationToken authentication) { //rozszerzam o model i oAth2 google

        UserProfileDto userProfile = userProfileMapper.toDto(authentication); // wyciągam potrzebne dane po przez maper i DTO


    //zapisuje do modelu thyaleaf dla pliku html potrzebne dane do wyswietlenia
        model.addAttribute("sub", userProfile.getSub()); // pobieram id sub z niego

        //Sprawdzam czy użytkownik ma dostep do systemu, pytam serwis który pytta dane czy istnieje taki uzytkownik pod takim googleid(sub) <- inaczej sub
        boolean exists = appUserService.userExists(userProfile.getSub());
        
        if (exists) {
            return "redirect:/dashboard"; //przekierowanie na inny endpoint
        } else {
            model.addAttribute("error", "Użytkownik o ID “" + userProfile.getSub() + "” nie istnieje.");
            return "check-id"; //przekazuje do folderu a nastepnie pliku resources/check-id
        }



    }

}
