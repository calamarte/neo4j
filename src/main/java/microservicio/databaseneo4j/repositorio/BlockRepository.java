package microservicio.databaseneo4j.repositorio;

import microservicio.databaseneo4j.block.Block;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface BlockRepository extends GraphRepository<Block> {

}
