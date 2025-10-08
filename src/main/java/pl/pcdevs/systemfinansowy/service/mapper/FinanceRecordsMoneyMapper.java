package pl.pcdevs.systemfinansowy.service.mapper;

import org.springframework.stereotype.Service;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class FinanceRecordsMoneyMapper {

    public List<String> format(List<FinanceRecord> financeRecords) {
        List<String> financeRecordsMoneyList = new ArrayList<>();

        Locale locale = new Locale("pl", "PL");
        NumberFormat formatter = NumberFormat.getNumberInstance(locale);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        for (final FinanceRecord financeRecord : financeRecords) {
            financeRecordsMoneyList.add(formatter.format(financeRecord.getMoney()));
        }
        return financeRecordsMoneyList;
    }

}
