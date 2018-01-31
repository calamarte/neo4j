package microservicio.databaseneo4j.controller;

import microservicio.databaseneo4j.block.Block;
import microservicio.databaseneo4j.repositorio.BlockRepository;
import org.neo4j.ogm.json.JSONException;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class ControllerBlockChange {
    @Autowired
    private BlockRepository blockRepository;

    @RequestMapping(path = "/blocks")
    public Iterable<Block> showBlocks(){
        return blockRepository.findAll();
    }

    @RequestMapping(value = "/block_by_hash/{hash}",method = RequestMethod.GET)
    public Block findBlockByHash(@PathVariable("hash") String hash){
        return blockRepository.findByHash(hash);
    }

    @RequestMapping(value = "/block_by_contain/{hash}",method = RequestMethod.GET)
    public List<Block> findBlockByContain(@PathVariable("hash") String hash){
        return blockRepository.findByHashContaining(hash);
    }

    @RequestMapping(value = "/block_by_interval/{after}/{before}",method = RequestMethod.GET)
    public List<Block> findByTimeBetween(@PathVariable("after") String after,@PathVariable("before") String before){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date afterDate = dateFormat.parse(after);
            Date beforeDate = dateFormat.parse(before);

            return blockRepository.findByTimeBetween(afterDate.getTime(),beforeDate.getTime());

        } catch (ParseException e) {
            return null;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> getData(@RequestBody String data){
        try {
            JSONObject jsonObject = new JSONObject(data);

            return new ResponseEntity<String>(data, HttpStatus.OK);
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

}
