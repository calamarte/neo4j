package microservicio.databaseneo4j.repositorio;

import microservicio.databaseneo4j.block.Block;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface BlockRepository extends GraphRepository<Block> {
    Block findByHash(String hash);
    List<Block> findByHashContaining(String hash);
    List<Block> findByTimeBetween(long after,long before);

    @Query("MATCH (n) RETURN n ORDER BY id(n) DESC LIMIT 1")
    Block getLastBlock();

    @Query("MATCH (c:Block {hash:{0}}), (p:Block {hash:{1}}) CREATE (c)-[:Chain]->(p)")
    void createChain(String currentBlockHash,String previousBlockHash);
}
