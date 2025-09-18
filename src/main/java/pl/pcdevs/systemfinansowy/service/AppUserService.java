package pl.pcdevs.systemfinansowy.service;

import org.springframework.stereotype.Service;
import pl.pcdevs.systemfinansowy.repository.AppUserRepository;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;


    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public boolean userExists(String googleId) {
        return appUserRepository.findByGoogleId(googleId).isPresent();
    }


}


