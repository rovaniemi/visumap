package visumap;

import com.google.gson.Gson;
import visumap.Algorithms.AStar;
import visumap.Algorithms.Dijkstra;
import visumap.Graph.GraphBuilder;
import visumap.Graph.Node;
import visumap.Statistic.Stats;
import java.util.Random;

/**
 * Router luokka hoitaa oikean tyyppisen kyselyn tuottamisen käyttäjälle,
 * eli valitsee oikean json -tiedoston mistä verkko luodaan, sekä valitsee käytettävän algoritmin.
 */


public class Router {

    private static final String DIJKSTRA = "dijkstra";
    private static final String ASTAR = "astar";
    private static final String JSON = "maps/graph.json";

    private static Node[] graph;

    public Router(String filename){
        this.graph = new GraphBuilder().createGraph(filename);
    }

    public Router(){
        this(JSON);
    }

    public String visualizeAlgorithm(String algorithm, int[] points){
        if( points[0] >= graph.length || points[0] < 0 || points[1] >= graph.length || points[1] < 0 ) return new Stats("Invalid points").getJson();
        if( algorithm.equals(DIJKSTRA)) return new Dijkstra(graph,points[0], points[1]).getStats().getJson();
        if( algorithm.equals(ASTAR)) return new AStar(graph,points[0], points[1]).getStats().getJson();
        return new Stats("Invalid algorithm").getJson();
    }

    public String randomPoints(){
        Random r = new Random();
        int max = this.graph.length;
        return new Gson().toJson(new int[]{r.nextInt(max), r.nextInt(max)}).toString();
    }
}
