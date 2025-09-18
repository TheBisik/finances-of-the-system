package pl.pcdevs.systemfinansowy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pcdevs.systemfinansowy.model.TransactionDate;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TransactionDateRepository extends JpaRepository<TransactionDate, Long> {
    Optional<TransactionDate> findByDate(LocalDate date);
}
