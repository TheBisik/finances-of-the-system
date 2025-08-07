package pl.pcdevs.systemfinansowy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.service.FinanceRecordService;

import java.util.List;

@RestController
@RequestMapping("api/v1/financerecords")  ///localhost/api/v1/financerecords?month=xyz&year=2000
public class FinanceRecordsController {

    private FinanceRecordService financeRecordService;
    boolean intIsInRange;


    @GetMapping
    public List<FinanceRecord> getAllFinanceRecords(@RequestParam(required = false) String month, @RequestParam(required = false) int year) {
        if(year <= 2024) intIsInRange = true;

        if (month == null || month.isEmpty() || !intIsInRange) {
            return null;
            }

        return financeRecordService.getRecordsByMonthAndYear(month, year);
    }

}
