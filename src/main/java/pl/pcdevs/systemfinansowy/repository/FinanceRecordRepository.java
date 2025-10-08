package pl.pcdevs.systemfinansowy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;

import java.time.LocalDate;
import java.util.List;



//Wyszukiwanie rekordów po miesiącu i roku
@Repository
public interface FinanceRecordRepository extends JpaRepository<FinanceRecord, Long> {

    List<FinanceRecord> findByTransactionDateBetween(LocalDate from, LocalDate to);



}