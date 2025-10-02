package pl.pcdevs.systemfinansowy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;


@Entity
public class FinanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private FinanceType financeType;
    private String name;
    private String description;
    private BigDecimal money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_date_id", nullable = false)
    @JsonManagedReference // ponieważ to klasa nadrzędna, jpa najpierw używa jej
    private TransactionDate transactionDate;

    public FinanceRecord() {}

    public FinanceRecord(Long id, FinanceType financeType, String name, String description, BigDecimal money, TransactionDate transactionDate) {
        this.id = id;
        this.financeType = financeType;
        this.name = name;
        this.description = description;
        this.money = money;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FinanceType getFinanceType() {
        return financeType;
    }

    public void setFinanceType(FinanceType financeType) {
        this.financeType = financeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public TransactionDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(TransactionDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FinanceRecord that)) return false;
        return Objects.equals(id, that.id) && financeType == that.financeType && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(money, that.money) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, financeType, name, description, money, transactionDate);
    }
}
