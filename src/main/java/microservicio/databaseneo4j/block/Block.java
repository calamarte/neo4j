package microservicio.databaseneo4j.block;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Block {

    @GraphId private Long id;

    private String hash;

    private Block() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Block(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String name) {
        this.hash = hash;
    }
}