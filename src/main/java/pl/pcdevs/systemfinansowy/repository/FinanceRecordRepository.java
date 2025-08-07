package pl.pcdevs.systemfinansowy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;

import java.time.Month;
import java.util.List;


//Wyszukiwanie rekordów po miesiącu i roku
@Repository
public interface FinanceRecordRepository extends JpaRepository<FinanceRecord, Long> {

    @Query("SELECT fr FROM FinanceRecord fr JOIN fr.transactionDate td WHERE FUNCTION('YEAR', td.date) = :year AND FUNCTION('MONTH', td.date) = :month")
    List<FinanceRecord> findByYearAndMonth(@Param("year") int year, @Param("month") Month month);
}
