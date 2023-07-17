package dev.danvega.pgadmin;

import dev.danvega.pgadmin.event.Event;
import dev.danvega.pgadmin.event.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EventRepository repository) {
		return args -> {
			// persist 1 event
			if(repository.count() == 0) {
				var event = new Event(1,
						"SpringOne at VMware Explore",
						"Join us at the biggest gathering of Spring enthusiasts, beginners and practitioners who build the apps that make the world run. This year, we are excited to have the developer-focused SpringOne event unite with the incredible VMware Explore 2023 in Las Vegas. Learn how Spring can take you from code to production at speed with its support for modern application architectures that run seamlessly on any cloud.",
						LocalDate.of(2023,8,21),
						LocalDate.of(2023,8,24),
						LocalDate.now().minusDays(180),
						LocalDate.now().minusDays(90),
						"Las Vegas, NV",
						"https://springone.io/");

				repository.save(event);
				log.info("Event created: " + event.getName());
			}
		};
	}

}
