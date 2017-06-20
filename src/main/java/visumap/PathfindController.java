package visumap;

import org.springframework.web.bind.annotation.*;
import visumap.Graph.Node;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PathfindController {

    private final String API_VERSION = "0.1V";

    private static Router router;

    public PathfindController(){
        this.router = new Router();
    }

    @ExceptionHandler({ Exception.class })
    public void handleError(HttpServletRequest req, Exception ex){
        System.out.println(req.toString());
        ex.printStackTrace();
    }

    @RequestMapping(value = "/shortest/" + API_VERSION + "/")
    public String getShortestPath(@RequestBody int[] json){
        return this.router.visualizeAlgorithm(json).getJson();
    }

    @RequestMapping(value = "/randompoints/" + API_VERSION + "/")
    public String getRandomPoints(){
        return this.router.randomPoints();
    }
}