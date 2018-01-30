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

    @RequestMapping(path = "/blocks")
    public Iterable<Block> showBlocks(){
        return blockRepository.findAll();
    }
}
