package pl.pcdevs.systemfinansowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pcdevs.systemfinansowy.dto.UserProfileDto;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.service.DateService;
import pl.pcdevs.systemfinansowy.service.FinanceService;
import pl.pcdevs.systemfinansowy.service.mapper.UserProfileMapper;


@Controller
public class SaldoController {

    private final FinanceService financeService;
    private final DateService dateService;

    private final UserProfileMapper userProfileMapper;

    @Autowired
    public SaldoController(FinanceService financeService, DateService dateService, UserProfileMapper userProfileMapper) {
        this.financeService = financeService;
        this.dateService = dateService;
        this.userProfileMapper = userProfileMapper;
    }

    @GetMapping("/saldo")
    public String saldo(Model model, OAuth2AuthenticationToken authentication) {


        UserProfileDto userProfile = userProfileMapper.toDto(authentication);

        model.addAttribute("name", userProfile.getName());
        model.addAttribute("picture", userProfile.getPicture());
        model.addAttribute("currentYear", dateService.getCurrentDate());
        return "auth/saldo";
    }

    @GetMapping("/saldo/edit")
    public String edit(Model model, @RequestParam Long id) {

        model.addAttribute("record", financeService.getRecordById(id));
        return "auth/edit";
    }

    @GetMapping("/saldo/add")
    public String edit(Model model) {

        return "auth/add";
    }
}
