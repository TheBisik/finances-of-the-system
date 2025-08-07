package pl.pcdevs.systemfinansowy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


//Wyszukiwanie rekordów po miesiącu i roku
@Repository
public interface FinanceRecordRepository extends JpaRepository<FinanceRecord, Long> {

    // Poprawne zapytanie za pomocą Query Method
    List<FinanceRecord> findByTransactionDate_TransactionDate_YearAndTransactionDate_TransactionDate_Month(int year, Month month);
}
