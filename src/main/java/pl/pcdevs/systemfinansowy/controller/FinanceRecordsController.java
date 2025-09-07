package pl.pcdevs.systemfinansowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.service.FinanceService;

import java.util.List;

@Controller
@RequestMapping("api/v1/financerecords")  ///localhost/api/v1/financerecords?month=may&year=2000
public class FinanceRecordsController {

    private final FinanceService financeService;
    boolean intIsInRange;

    public FinanceRecordsController(FinanceService financeService) {
        this.financeService = financeService;
    }


    @GetMapping
    public List<FinanceRecord> getAllFinanceRecords(@RequestParam String month, @RequestParam int year) {
        return financeService.getRecordsByMonthAndYear(month, year);
    }



}
