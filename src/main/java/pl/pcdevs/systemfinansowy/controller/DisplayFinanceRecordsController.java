package pl.pcdevs.systemfinansowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.pcdevs.systemfinansowy.dto.UserProfileDto;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.service.DateService;
import pl.pcdevs.systemfinansowy.service.FinanceService;
import pl.pcdevs.systemfinansowy.service.mapper.FinanceRecordsMoneyMapper;
import pl.pcdevs.systemfinansowy.service.mapper.UserProfileMapper;

import java.util.List;

@Controller
public class DisplayFinanceRecordsController {

    private final DateService dateService;
    private final UserProfileMapper userProfileMapper;

    private final FinanceService financeService;
    private final FinanceRecordsMoneyMapper financeRecordsMoneyMapper;


    @Autowired
    public DisplayFinanceRecordsController(DateService dateService, UserProfileMapper userProfileMapper, FinanceService financeService, FinanceRecordsMoneyMapper financeRecordsMoneyMapper) {
        this.dateService = dateService;
        this.userProfileMapper = userProfileMapper;
        this.financeService = financeService;
        this.financeRecordsMoneyMapper = financeRecordsMoneyMapper;
    }

    @GetMapping("/saldo/financerecords/{year}/{monthInt}")
    public String displayFinanceRecordsController(Model model, OAuth2AuthenticationToken authentication, @PathVariable int monthInt, @PathVariable int year) {

        UserProfileDto userProfile = userProfileMapper.toDto(authentication);


        model.addAttribute("name", userProfile.getName());
        model.addAttribute("picture", userProfile.getPicture());
        model.addAttribute("currentYear", dateService.getCurrentDate());


        List<FinanceRecord> financeRecordList = financeService.getRecordsByMonthAndYear(year, monthInt);
        model.addAttribute("financeRecordsMoneyList", financeRecordsMoneyMapper.format(financeRecordList));


        model.addAttribute("financeRecords", financeRecordList);
        model.addAttribute("monthName", financeService.getMonthNameByNumber(monthInt));

        return "auth/financerecord";
    }

}
