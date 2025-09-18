package pl.pcdevs.systemfinansowy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;


//Wyszukiwanie rekordów po miesiącu i roku
@Repository
public interface FinanceRecordRepository extends JpaRepository<FinanceRecord, Long> {

    /**
     * Używa niestandardowego zapytania SQL, aby uzyskać rekordy finansowe na podstawie roku i miesiąca.
     * Używamy funkcji EXTRACT() specyficznej dla PostgreSQL.
     * @param year rok
     * @param month miesiąc
     * @return lista rekordów finansowych
     */
    @Query("SELECT fr FROM FinanceRecord fr JOIN fr.transactionDate td WHERE EXTRACT(YEAR FROM td.date) = :year AND EXTRACT(MONTH FROM td.date) = :month")
    List<FinanceRecord> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    Object removeById(Long id);
}