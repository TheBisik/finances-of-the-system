package pl.pcdevs.systemfinansowy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<FinanceRecord> getRecordsByMonthAndYear(String monthName, int year) {
        Month month = getMonthEnum(monthName);
        // Błąd: przekazywano obiekt Month, gdy metoda oczekuje int.
        // Poprawka: używamy month.getValue(), aby uzyskać numer miesiąca (1-12).
        return repository.findByYearAndMonth(year, month.getValue());
    }

    /**
     * Konwertuje polską nazwę miesiąca (bez znaków diakrytycznych) na obiekt Month.
     * @param monthName nazwa miesiąca, np. "styczen"
     * @return odpowiedni obiekt Month
     */
    private Month getMonthEnum(String monthName) {
        return switch (monthName.toLowerCase(new Locale("pl", "PL"))) {
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