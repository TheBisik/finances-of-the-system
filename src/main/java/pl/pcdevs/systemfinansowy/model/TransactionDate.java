package pl.pcdevs.systemfinansowy.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class TransactionDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;
    private String month;
    private String year;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionDate")
    private List<FinanceRecord> transactionDate = new ArrayList<>();

    public TransactionDate() {}
    public TransactionDate(Long id, String day, String month, String year) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
