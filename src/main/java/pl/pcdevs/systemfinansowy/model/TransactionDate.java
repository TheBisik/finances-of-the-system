package pl.pcdevs.systemfinansowy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class TransactionDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transactionDate")
    @JsonBackReference // ponieważ to klasa podrzędna
    private List<FinanceRecord> financeRecords = new ArrayList<>();

    public TransactionDate() {}
    public TransactionDate(LocalDate date) {
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<FinanceRecord> getFinanceRecords() {
        return financeRecords;
    }

    public void setFinanceRecords(List<FinanceRecord> financeRecords) {
        this.financeRecords = financeRecords;
    }
}