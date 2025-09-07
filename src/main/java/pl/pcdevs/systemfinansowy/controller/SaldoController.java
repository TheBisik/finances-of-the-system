package pl.pcdevs.systemfinansowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.service.DateService;
import pl.pcdevs.systemfinansowy.service.FinanceService;



@Controller
public class SaldoController {

    private final FinanceService financeService;
    private final DateService dateService;

    @Autowired
    public SaldoController(FinanceService financeService, DateService dateService) {
        this.financeService = financeService;
        this.dateService = dateService;
    }

    @GetMapping("/saldo")
    public String saldo(Model model) {

        //todo Zaprojektować plik html, listowanie danych z bazdy danych oraz (może nowy obiekt) sume która została podliczona z wszystkich kwot.
        //todo zacząć testować kod !!!

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
