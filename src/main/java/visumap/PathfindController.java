package visumap;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PathfindController {

    private final String API_VERSION = "0.1v";

    private static Router router;

    public PathfindController(){
        this.router = new Router();
    }

    @ExceptionHandler({ Exception.class })
    public void handleError(HttpServletRequest req, Exception ex){
        System.out.println(req.toString());
        ex.printStackTrace();
    }

    @RequestMapping(value = "/" + API_VERSION + "/shortest/")
    public String getShortestPath(@RequestBody ShortestJsonRequest json){
        return this.router.visualizeAlgorithm(json.algorithm, json.points);
    }

    @RequestMapping(value = "/" + API_VERSION + "/randompoints/")
    public String getRandomPoints(){
        return this.router.randomPoints();
    }
}