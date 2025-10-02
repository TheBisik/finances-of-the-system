package pl.pcdevs.systemfinansowy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pcdevs.systemfinansowy.model.AppUser;
import pl.pcdevs.systemfinansowy.repository.AppUserRepository;
import pl.pcdevs.systemfinansowy.service.AppUserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @Test
    public void shouldReturnTrueIfUserExists() {
        //Arrange
        String subIdGoogle = "1234567890";
        Optional<AppUser> expectedAppUser = Optional.of(new AppUser(subIdGoogle));

        //Act
        when(appUserRepository.findByGoogleId(subIdGoogle)).thenReturn(expectedAppUser);

        //Assert
        assertTrue(appUserService.userExists(subIdGoogle));
        verify(appUserRepository).findByGoogleId(subIdGoogle);
    }

}
