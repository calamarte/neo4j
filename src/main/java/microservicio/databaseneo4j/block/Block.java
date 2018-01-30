package microservicio.databaseneo4j.block;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Date;

@NodeEntity
public class Block {

    @GraphId private Long id;

    @Property private String hash;
    @Property private String previusHash;
    @Property private Date time;

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

    public String getPreviusHash() {
        return previusHash;
    }

    public void setPreviusHash(String previusHash) {
        this.previusHash = previusHash;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}