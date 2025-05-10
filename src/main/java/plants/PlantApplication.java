package plants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//marks this class as Spring Boot application class
@SpringBootApplication
public class PlantApplication {

	//main method which starts the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(PlantApplication.class, args);

	}
}