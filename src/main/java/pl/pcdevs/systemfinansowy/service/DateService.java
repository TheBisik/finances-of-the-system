package pl.pcdevs.systemfinansowy.service;


import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {

    private LocalDate date;

    public int getCurrentDate() {
        date = LocalDate.now();
        return date.getYear();
    }
}
