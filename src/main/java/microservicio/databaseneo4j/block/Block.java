package microservicio.databaseneo4j.block;

import org.neo4j.ogm.annotation.*;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Block {

    @GraphId private Long id;

    @Index(primary = true,unique = true)
    private int index;

    @Property private String hash;
    @Property private String previousHash;
    @Property private long time;
    @Property private String data;
    @Property private int nonce;

    @Relationship(type = "Chain",direction = Relationship.DIRECTION)
    private Set<Block> chain = new HashSet<Block>();

    public void chainBlock(Block block) {
        chain.add(block);
    }


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

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Set<Block> getChain() {
        return chain;
    }

    public void setChain(Set<Block> chain) {
        this.chain = chain;
    }
}