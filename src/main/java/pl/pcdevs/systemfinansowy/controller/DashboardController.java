package pl.pcdevs.systemfinansowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pcdevs.systemfinansowy.dto.UserProfileDto;
import pl.pcdevs.systemfinansowy.service.mapper.UserProfileMapper;

@Controller
public class DashboardController {

    private final UserProfileMapper userProfileMapper;

    @Autowired
    public DashboardController(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, OAuth2AuthenticationToken authentication) {

        UserProfileDto userProfile = userProfileMapper.toDto(authentication);

        model.addAttribute("sub", userProfile.getSub());
        model.addAttribute("name", userProfile.getName());
        model.addAttribute("picture", userProfile.getPicture());

        return "auth/dashboard";
    }

}


