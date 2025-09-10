package pl.pcdevs.systemfinansowy;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.model.TransactionDate;
import pl.pcdevs.systemfinansowy.repository.FinanceRecordRepository;
import pl.pcdevs.systemfinansowy.service.FinanceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static pl.pcdevs.systemfinansowy.model.FinanceType.REVENUE;

@ExtendWith(MockitoExtension.class)
public class FinanceServiceTest {

    @Mock
    private FinanceRecordRepository financeRecordRepository;

    @InjectMocks
    private FinanceService financeService;

    @Test
    public void shouldReturnAllFinanceRecords() {
        //Arrange - tworzymy zmienne w kodzie do testowania aplikacji wraz z wartością zwracaną
        String monthTypeString = "styczen";
        int monthTypeInt = 1;
        int year = 1000;

        FinanceRecord financeRecordNr1 = new FinanceRecord(1L, REVENUE, "Testowy1", "Testowy opis", BigDecimal.valueOf(2000), new TransactionDate(LocalDate.now()));
        FinanceRecord financeRecordNr2 = new FinanceRecord(2L, REVENUE, "Testowy2", "Testowy opis", BigDecimal.valueOf(2000), new TransactionDate(LocalDate.now()));
        List<FinanceRecord> expectedFinanceRecordsList = List.of(financeRecordNr1, financeRecordNr2); //oczekiwana wartość
        //jesli repo zadziała to zwróć poprawną liste
        when(financeRecordRepository.findByYearAndMonth(year, monthTypeInt)).thenReturn(expectedFinanceRecordsList);

        //Act - testujemy serwis + repo
        List<FinanceRecord> resultList = financeService.getRecordsByMonthAndYear(monthTypeString, year);

        //Assert - zwracamy wartości do porównania (1. poprawna wartość 2. wartość testowana)
        assertEquals(expectedFinanceRecordsList, resultList);
    }

    //todo Made more test methods of FinanceService
}
