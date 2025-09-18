package pl.pcdevs.systemfinansowy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pcdevs.systemfinansowy.service.DateService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateServiceTest {

    @InjectMocks
    private DateService dateService;

    @Test
    public void shouldReturnCurrentDate() {

        //Arrange
        LocalDate expectedCurrentDate = LocalDate.now();

        //Act
        //todo ask ai if i only test the method in service i must make Act section

        //Assert
        assertEquals(expectedCurrentDate, dateService.getCurrentDate());
    }

}
