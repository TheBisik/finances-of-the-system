package pl.pcdevs.systemfinansowy.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pcdevs.systemfinansowy.service.FinanceRecordService;

@Controller
public class SaldoController {

    private FinanceRecordService financeRecordService;

    @GetMapping("/saldo")
    public String saldo(Model model) {
        //todo Zaprojektować plik html, listowanie danych z bazdy danych oraz (może nowy obiekt) sume która została podliczona z wszystkich kwot.
        //todo zacząć testować kod !!!
        return "auth/saldo";
    }

}
