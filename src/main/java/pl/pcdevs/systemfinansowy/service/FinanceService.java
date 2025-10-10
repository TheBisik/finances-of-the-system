package pl.pcdevs.systemfinansowy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.model.TransactionDate;
import pl.pcdevs.systemfinansowy.repository.FinanceRecordRepository;
import pl.pcdevs.systemfinansowy.repository.TransactionDateRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class FinanceService {


    private final FinanceRecordRepository repository;
    private final TransactionDateRepository transactionDateRepository;

    @Autowired
    public FinanceService(FinanceRecordRepository repository, TransactionDateRepository transactionDateRepository) {
        this.repository = repository;
        this.transactionDateRepository = transactionDateRepository;
    }

    private List<FinanceRecord> findByTransactionDate(int monthNumber, int year) {

        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Month number must be between 1 and 12");
        }

        LocalDate startDate = LocalDate.of(year, monthNumber, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return repository.findByTransactionDateBetween(startDate, endDate);
    }

    public List<FinanceRecord> getRecordsByMonthAndYear(String monthName, int year) {
        Month month = getMonthEnumFromString(monthName);
        return findByTransactionDate(month.getValue(), year);
    }

    public List<FinanceRecord> getRecordsByMonthAndYear(int monthNumber, int year) {
        return findByTransactionDate(monthNumber, year);
    }

    /**
     * Converting Polish name of month (without diakrytycznych) to object Month.
     * @param monthName name of month, "styczen"
     * @return equals object Month
     */
    private Month getMonthEnumFromString(String monthName) {
        return switch (monthName.toLowerCase(Locale.ROOT)) {
            case "styczen" -> Month.JANUARY;
            case "luty" -> Month.FEBRUARY;
            case "marzec" -> Month.MARCH;
            case "kwiecien" -> Month.APRIL;
            case "maj" -> Month.MAY;
            case "czerwiec" -> Month.JUNE;
            case "lipiec" -> Month.JULY;
            case "sierpien" -> Month.AUGUST;
            case "wrzesien" -> Month.SEPTEMBER;
            case "pazdziernik" -> Month.OCTOBER;
            case "listopad" -> Month.NOVEMBER;
            case "grudzien" -> Month.DECEMBER;
            default -> throw new IllegalArgumentException("Nieprawidłowa nazwa miesiąca: " + monthName);
        };
    }

    public String getMonthNameByNumber(int monthInt) {
        if (monthInt <= 1 || monthInt >= 12) {
            throw new IllegalArgumentException("Month number must be between 1 and 12");
        }
        return switch (monthInt) {
            case 1 -> "Styczeń";
            case 2 -> "Luty";
            case 3 -> "Marzec";
            case 4 -> "Kwiecień";
            case 5 -> "Maj";
            case 6 -> "Czerwiec";
            case 7 -> "Lipiec";
            case 8 -> "Sierpień";
            case 9 -> "Wrzesień";
            case 10 -> "Październik";
            case 11 -> "Listopad";
            case 12 -> "Grudzień";
            default -> null;
        };
    }



    @Transactional
    public FinanceRecord saveRecord(FinanceRecord record) {
        if (record.getTransactionDate() == null) {
            LocalDate today = LocalDate.now();

            Optional<TransactionDate> existingDate = transactionDateRepository.findByDate(today);

            TransactionDate transactionDate = existingDate.orElseGet(() -> {
                TransactionDate newDate = new TransactionDate(today);
                return transactionDateRepository.save(newDate);
            });

            record.setTransactionDate(transactionDate);
        }
        return repository.save(record);
    }

    public FinanceRecord getRecordById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }


}

//todo Rozwiązać problem z serwisem, błąd logiczny danych w BD postgresql
//todo 1. Ustalić jaki typ danych jest zapisywany w polach DB
//todo 2. Okreslić ja dane zapsiują się do DB
//todo 3. ujednolicić system z DB