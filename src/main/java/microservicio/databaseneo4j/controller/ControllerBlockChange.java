package microservicio.databaseneo4j.controller;

import microservicio.databaseneo4j.block.Block;
import microservicio.databaseneo4j.repositorio.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerBlockChange {
    @Autowired
    private BlockRepository blockRepository;
    private int indice = 0;

    @RequestMapping(path = "/blocks")
    public Iterable<Block> showBlocks(){

        return blockRepository.findAll();
//        String hash = "00"+indice;
//        for (Block block :blocks) {
//            textString.append(block);
//            textString.append("<br>");
//        }
//        blockRepository.save(new Block(hash));
//        indice++;
//        return blockRepository.findByHash(hash);
    }
}
