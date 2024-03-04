package inc.temp.right.always.restsqlapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "inc.temp.right.always.restsqlapi.repositories")
public class RestSqlApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestSqlApiApplication.class, args);
	}
}
