package pathfinder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pathfinder.Graph.Graph;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

@RestController
public class PathfindController {

    private Graph graph;

    public PathfindController(){
        this.graph = new Graph();
    }

    @RequestMapping("/graph")
    public long getGraph(@RequestParam(value="i", defaultValue="0") int i){
        return graph.getGraph()[221].get(0).getWeight(); //1197
    }
}