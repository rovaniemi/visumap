package pathfinder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pathfinder.Graph.Graph;

@RestController
public class PathfindController {

    private Graph graph;

    public PathfindController(){
        this.graph = new Graph();
    }

    @RequestMapping("/graph")
    public Graph getGraph(@RequestParam(value="i", defaultValue="0") int i) {
         return graph;
    }
}