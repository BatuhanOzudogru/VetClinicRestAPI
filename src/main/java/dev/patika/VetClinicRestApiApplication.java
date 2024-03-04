package dev.patika;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class VetClinicRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetClinicRestApiApplication.class, args);
	}

}
