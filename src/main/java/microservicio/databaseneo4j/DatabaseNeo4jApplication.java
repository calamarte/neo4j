package microservicio.databaseneo4j;

import microservicio.databaseneo4j.block.Block;
import microservicio.databaseneo4j.repositorio.BlockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class DatabaseNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseNeo4jApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(BlockRepository blockRepository) {
		return args -> {

			blockRepository.deleteAll();

			Block block = new Block("123456");


			blockRepository.save(block);

		};
	}
}