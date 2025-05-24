package pl.pcdevs.systemfinansowy.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import pl.pcdevs.systemfinansowy.model.AppUser;
import pl.pcdevs.systemfinansowy.model.DataGenarate;
import pl.pcdevs.systemfinansowy.repository.AppUserRepository;
import pl.pcdevs.systemfinansowy.repository.DataGenarateRepostiory;

@Service
public class DataGenerateService {
    private final DataGenarateRepostiory dataGenarateRepostiory;
    private final AppUserRepository appUserRepository;

    public DataGenerateService(DataGenarateRepostiory dataGenarateRepostiory, AppUserRepository appUserRepository) {
        this.dataGenarateRepostiory = dataGenarateRepostiory;
        this.appUserRepository = appUserRepository;
    }

    public String aplicationRun() {

        var tempInformation = dataGenarateRepostiory.findById(1);
        if (tempInformation.isPresent()) {
            var temp = "Aplication Data is exist and succesfully loaded";
            return temp;
        }
        else {
            dataGenarateRepostiory.save(new DataGenarate(1, true));
            appUserRepository.save(new AppUser("118421385628288522268"));
            var temp = "Aplication Data has be generated and now exist";
            return temp;
        }

    }
}
