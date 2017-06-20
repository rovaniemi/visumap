package visumap;

import com.google.gson.Gson;
import visumap.Graph.GraphBuilder;
import visumap.Graph.Node;
import visumap.Graph.Node2;
import visumap.Statistic.NodeJson;
import visumap.Statistic.Stats;
import visumap.Tools.CoordinateDistance;

import java.util.Map;
import java.util.Random;


/**
 * Router luokka hoitaa oikean tyyppisen kyselyn tuottamisen käyttäjälle,
 * eli valitsee oikean json -tiedoston mistä verkko luodaan, sekä valitsee käytettävän algoritmin.
 */


public class Router {

    private static final String DIJKSTRA = "dijkstra";
    private static final String ASTAR = "astar";
    private static final String FINLAND= "helsinki.json";
    private static final String PATH = "maps/";

    private Node2[] graph;

    /**
     * Alustetaan map graphs, joka sisältää verkkoja aina kaupunkia kohtaan.
     * Alustetaan map nodes, joka sisältää kaikki kaupungin nodet.
     */

    public Router(){
        this.graph = new GraphBuilder().createGraph(PATH + FINLAND);
    }

    public Stats visualizeAlgorithm(int[] points){
        if(points[0] >= graph.length || points[1] >= graph.length) return new Stats("Invalid points");
        return new Stats();
    }

    public String randomPoints(){
        Random r = new Random();
        int max = this.graph.length;
        return new Gson().toJson(new int[]{r.nextInt(max), r.nextInt(max)}).toString();
    }
}
