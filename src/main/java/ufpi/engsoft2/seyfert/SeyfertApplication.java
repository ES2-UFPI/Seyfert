package ufpi.engsoft2.seyfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"ufpi.engsoft2.seyfert.domain.model"})
@SpringBootApplication
public class SeyfertApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeyfertApplication.class, args);
	}

}
