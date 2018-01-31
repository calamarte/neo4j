package microservicio.databaseneo4j.generator;

import com.google.common.hash.Hashing;
import microservicio.databaseneo4j.block.Block;
import microservicio.databaseneo4j.repositorio.BlockRepository;
import org.neo4j.ogm.json.JSONException;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class BlockGenerator {
    @Autowired
    private BlockRepository blockRepository;

    @Value("${hash.difficulty}")
    private String difficulty;

    public void createBlock(JSONObject data){
        JSONObject toHash = new JSONObject();
        Date currentTime = new Date();
        long currentUnixTime = currentTime.getTime();

        try {
            String previousHash;
            try {
                previousHash = blockRepository.getLastBlock().getHash();
            }catch (NullPointerException e){
                previousHash = null;
            }

            toHash.put("previousHash",previousHash);
            toHash.put("time",currentUnixTime);
            toHash.put("data",data);

            String hash;
            int nonce = 0;

            while (true){
                toHash.put("nonce",nonce);
                hash = generateHash(toHash.toString());

                if(hash.startsWith(difficulty)){
                    break;
                }
                nonce++;
            }
            System.out.println(toHash.toString());
            System.out.println(hash);

            Block block = new Block(hash);
            block.setPreviousHash(previousHash);
            block.setTime(currentUnixTime);
            block.setData(data.toString());
            block.setNonce(nonce);

            if(previousHash != null){
                block.setIndex(blockRepository.getLastBlock().getIndex()+1);
            }

            saveBlock(block);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String generateHash(String toHash) {
        return Hashing.sha256().hashString(toHash, StandardCharsets.UTF_8).toString();
    }

    private void saveBlock(Block block){
        blockRepository.save(block);

        if(block.getPreviousHash() != null){
            blockRepository.createChain(block.getHash(),block.getPreviousHash());
        }
    }
}
