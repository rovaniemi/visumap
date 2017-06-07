package visumap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PathfindController {

    private final String API_VERSION = "0.1V";

    private static Router router;

    public PathfindController(){
        this.router = new Router();
    }

    @RequestMapping(value = "/sortest/" + API_VERSION + "/")
    public String getSortestPath(@RequestParam Map<String,String> params){
        String algorithm = params.get("algorithm");
        Double startNodeLat = Double.parseDouble(params.get("sLat"));
        Double startNodeLon = Double.parseDouble(params.get("sLon"));
        Double goalLat = Double.parseDouble(params.get("gLat"));
        Double goalLon = Double.parseDouble(params.get("gLon"));
        String city = params.get("city");
        return "";
    }
}