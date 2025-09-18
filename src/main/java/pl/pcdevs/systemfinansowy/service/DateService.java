package pl.pcdevs.systemfinansowy.service;


import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {

    public int getCurrentDate() {
        LocalDate date = LocalDate.now();
        return date.getYear();
    }
}
