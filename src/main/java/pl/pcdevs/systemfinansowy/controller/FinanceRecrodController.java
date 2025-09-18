package pl.pcdevs.systemfinansowy.controller;

import org.springframework.web.bind.annotation.*;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.service.FinanceService;

@RestController
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

//    @RequestBody - oczekuje body w formacie JSON lub XML
//    @ModelAttribute - oczekuje danych zakodowanych w formularz application/x-www-form-urlencoded

    @PostMapping
    public String addFinanceRecord(@ModelAttribute FinanceRecord financeRecord) {
        financeService.saveRecord(financeRecord);
        return "<script>window.location.href = '/saldo';</script>";
    }

    @DeleteMapping
    public void deleteFinanceRecord(@PathVariable Long id) {
        financeService.deleteRecord(id);
    }
}
