package pl.pcdevs.systemfinansowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.pcdevs.systemfinansowy.service.DataGenerateService;

@SpringBootApplication
public class SystemFinansowyApplication {

	public static void main(String[] args) {
		// uruchomienie kontekstu
		ConfigurableApplicationContext context =
				SpringApplication.run(SystemFinansowyApplication.class, args);

		// pobranie beana i wywołanie metody
		DataGenerateService service = context.getBean(DataGenerateService.class);
		System.out.println(service.aplicationRun());  // zakładam, że tak nazywasz metodę
	}

}
