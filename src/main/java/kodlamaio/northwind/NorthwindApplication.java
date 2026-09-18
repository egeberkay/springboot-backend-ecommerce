package kodlamaio.northwind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class NorthwindApplication {

	public static void main(String[] args) {

		SpringApplication.run(NorthwindApplication.class, args);

	}

}
