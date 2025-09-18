package pl.pcdevs.systemfinansowy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.repository.FinanceRecordRepository;

import java.time.Month;
import java.util.List;
import java.util.Locale;

@Service
public class FinanceRecordService {

    private FinanceRecordRepository repository;

    @Autowired
    public FinanceRecordService(FinanceRecordRepository repository) {
        this.repository = repository;
    }

    public List<FinanceRecord> getRecordsByMonthAndYear(String monthName, int year){

        Month month = getMonthEnum(monthName);


        return repository.findByYearAndMonth(year, month);
    }

    private Month getMonthEnum(String monthName) {
        return switch (monthName.toLowerCase(new Locale("pl", "PL"))) {
            case "styczeń" -> Month.JANUARY;
            case "luty" -> Month.FEBRUARY;
            case "marzec" -> Month.MARCH;
            case "kwiecień" -> Month.APRIL;
            case "maj" -> Month.MAY;
            case "czerwiec" -> Month.JUNE;
            case "lipiec" -> Month.JULY;
            case "sierpień" -> Month.AUGUST;
            case "wrzesień" -> Month.SEPTEMBER;
            case "październik" -> Month.OCTOBER;
            case "listopad" -> Month.NOVEMBER;
            case "grudzień" -> Month.DECEMBER;
            default -> throw new IllegalArgumentException("Nieprawidłowa nazwa miesiąca: " + monthName);
        };
    }


}
