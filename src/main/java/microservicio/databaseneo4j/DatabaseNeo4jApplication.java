package microservicio.databaseneo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class DatabaseNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseNeo4jApplication.class, args);
	}

//	@Bean
//	CommandLineRunner demo(BlockRepository blockRepository) {
//		return args -> {
//
//		};
//	}
}