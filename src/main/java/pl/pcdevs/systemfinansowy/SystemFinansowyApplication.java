package pl.pcdevs.systemfinansowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SystemFinansowyApplication {

	public static void main(String[] args) {
		// uruchomienie kontekstu
		ConfigurableApplicationContext context =
				SpringApplication.run(SystemFinansowyApplication.class, args);

	}

}
