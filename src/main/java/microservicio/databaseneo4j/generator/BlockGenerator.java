package microservicio.databaseneo4j.generator;

import com.google.common.hash.Hashing;
import microservicio.databaseneo4j.block.Block;
import microservicio.databaseneo4j.repository.BlockRepository;
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
        JSONObject hashData = new JSONObject();
        Date currentTime = new Date();
        long currentUnixTime = currentTime.getTime();

        try {
            String previousHash;
            try {
                previousHash = blockRepository.getLastBlock().getHash();
            }catch (NullPointerException e){
                previousHash = null;
            }

            hashData.put("previousHash",previousHash);
            hashData.put("time",currentUnixTime);
            hashData.put("data",data);

            String hash;
            int nonce = 0;

            while (true){
                hashData.put("nonce",nonce);
                hash = generateHash(hashData.toString());

                if(hash.startsWith(difficulty)){
                    break;
                }
                nonce++;
            }

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

    private String generateHash(String preHash) {
        return Hashing.sha256().hashString(preHash, StandardCharsets.UTF_8).toString();
    }

    private void saveBlock(Block block){
        if(block.getPreviousHash() != null){
            block.chainBlock(blockRepository.getLastBlock());
        }
        blockRepository.save(block);
    }
}
