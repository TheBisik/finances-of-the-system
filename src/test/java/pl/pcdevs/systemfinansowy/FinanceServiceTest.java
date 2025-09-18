package pl.pcdevs.systemfinansowy;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pcdevs.systemfinansowy.model.FinanceRecord;
import pl.pcdevs.systemfinansowy.model.TransactionDate;
import pl.pcdevs.systemfinansowy.repository.FinanceRecordRepository;
import pl.pcdevs.systemfinansowy.repository.TransactionDateRepository;
import pl.pcdevs.systemfinansowy.service.FinanceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pl.pcdevs.systemfinansowy.model.FinanceType.REVENUE;

@ExtendWith(MockitoExtension.class)
public class FinanceServiceTest {

    @Mock
    private FinanceRecordRepository financeRecordRepository;

    @Mock
    private TransactionDateRepository transactionDateRepository;

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

        //Act - testujemy serwis + repo
        when(financeRecordRepository.findByYearAndMonth(year, monthTypeInt)).thenReturn(expectedFinanceRecordsList);
        List<FinanceRecord> resultList = financeService.getRecordsByMonthAndYear(monthTypeString, year);

        //Assert - zwracamy wartości do porównania (1. poprawna wartość 2. wartość testowana)
        assertEquals(expectedFinanceRecordsList, resultList);
    }

    //todo Made more test methods of FinanceService
    @Test
    public void shouldReturnFinanceRecord() {
        //arrange
        Long idForFinanceRecord = 1L;
        Long idForNull = 3L;


        Optional<FinanceRecord> exceptedRecord1 = Optional.of(new FinanceRecord(1L, REVENUE, "Testowy1", "Testowy opis", BigDecimal.valueOf(2000), new TransactionDate(LocalDate.now())));
        Optional<FinanceRecord> exceptedRecord2 = Optional.empty();

        //Act
        when(financeRecordRepository.findById(idForFinanceRecord)).thenReturn(exceptedRecord1);
        when(financeRecordRepository.findById(idForNull)).thenReturn(exceptedRecord2);

        //Assert
        assertEquals(exceptedRecord1.get(), financeService.getRecordById(idForFinanceRecord));

        assertEquals(null, financeService.getRecordById(idForNull));

    }

    @Test
    public void shouldCallDeleteMethodWhenRecordIsDeleted() {
        Long idForFinanceRecord = 5L;

        financeService.deleteRecord(idForFinanceRecord);

        verify(financeRecordRepository).deleteById(idForFinanceRecord);

    }

    //saveRecord Tests
    // 1. Save with date
    // 2. Save without date in saved object

    @Test //1.
    public void shouldSaveFinanceRecordWithDate() {
        //arrange
        Long idForFinanceRecord = 5L;

        FinanceRecord exceptedRecord = new FinanceRecord(1L, REVENUE, "Testowy1", "Testowy opis", BigDecimal.valueOf(2000), new TransactionDate(LocalDate.now()));
        FinanceRecord record = exceptedRecord;

        //act

        when(financeRecordRepository.save(record)).thenReturn(record);

        //assert
        assertEquals(exceptedRecord, financeService.saveRecord(record));

    }



    @Test //2.
    public void shouldSaveRecordAndCreateNewTransactionDateWhenDateIsNull() {
        // Arrange
        // 1. Obiekt wejściowy z datą = null
        FinanceRecord record = new FinanceRecord(null, REVENUE, "Testowy", "Opis", BigDecimal.valueOf(100), null);

        // 2. Symulacja nowej, zapisanej daty
        TransactionDate newDate = new TransactionDate(LocalDate.now());
        newDate.setId(1L);

        // 3. Obiekt, który serwis ma zwrócić (z uzupełnioną datą i ID)
        FinanceRecord expectedSavedRecord = new FinanceRecord(1L, REVENUE, "Testowy", "Opis", BigDecimal.valueOf(100), newDate);

        // 4. Konfiguracja mocków
        when(transactionDateRepository.findByDate(any(LocalDate.class))).thenReturn(Optional.empty());
        when(transactionDateRepository.save(any(TransactionDate.class))).thenReturn(newDate);
        when(financeRecordRepository.save(any(FinanceRecord.class))).thenReturn(expectedSavedRecord);

        // Act
        // Wywołaj metodę w serwisie i zapisz jej wynik
        FinanceRecord result = financeService.saveRecord(record);

        // Assert
        // 1. Sprawdź, czy zwrócono oczekiwany obiekt
        assertEquals(expectedSavedRecord, result);

        // 2. Sprawdź, czy metody zostały wywołane
        verify(transactionDateRepository).findByDate(any(LocalDate.class));
        verify(transactionDateRepository).save(any(TransactionDate.class));
        verify(financeRecordRepository).save(any(FinanceRecord.class));
    }


}
