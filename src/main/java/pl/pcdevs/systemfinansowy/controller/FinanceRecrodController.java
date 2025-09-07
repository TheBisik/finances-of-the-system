package pl.pcdevs.systemfinansowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.service.FinanceService;

@Controller
@RequestMapping("api/v1/financerecord")
public class FinanceRecrodController {


    private final FinanceService financeService;

    // To jest najlepsza metoda wstrzykiwania zależności
    public FinanceRecrodController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping
    public FinanceRecord getFinanceRecord(@RequestParam Long id) {
        return financeService.getRecordById(id);
    }

    @PostMapping
    public String addFinanceRecord(@RequestBody FinanceRecord financeRecord) {
        financeService.saveRecord(financeRecord);
        return "redirect:/saldo";
    }

    @DeleteMapping
    public void deleteFinanceRecord(@PathVariable Long id) {
        financeService.deleteRecord(id);
    }
}
