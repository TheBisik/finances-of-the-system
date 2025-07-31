package pl.pcdevs.systemfinansowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/auth/dashboard")
    public String dashboard() {

        return "/auth/dashboard";
    }

}
