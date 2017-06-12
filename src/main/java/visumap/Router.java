package visumap;

import visumap.Algorithms.AStar;
import visumap.Algorithms.Dijkstra;
import visumap.Graph.GraphBuilder;
import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Tools.CoordinateDistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Router luokka hoitaa oikean tyyppisen kyselyn tuottamisen käyttäjälle,
 * eli valitsee oikean json -tiedoston mistä verkko luodaan, sekä valitsee käytettävän algoritmin.
 */


public class Router {

    private static final String DIJKSTRA = "getShortestPath";
    private static final String ASTAR = "getShortestPath";
    private static final String TORNIO = "tornio-pretty.json";
    private static final String ROVANIEMI = "rovaniemi.json";
    private static final String HELSINKI = "helsinki.json";
    private static final String IVALO = "ivalo-pretty.json";
    private static final String TURKU = "turku.json";
    private static final String PATH = "maps/";

    private Map<String, List<Weight>[]> graphs;
    private Map<String, Map<Integer, Node>> nodes;

    public Router(){
        this.graphs = new HashMap<>();
        this.nodes = new HashMap<>();
        initialize();
    }

    private void initialize(){
        GraphBuilder graphBuilder = new GraphBuilder();
        initializeGraphs(graphBuilder);
        initializeNodes(graphBuilder);
    }

    private void initializeGraphs(GraphBuilder graphBuilder){
        this.graphs.put("tornio", graphBuilder.createGraph(PATH + TORNIO));
        /* this.graphs.put("rovaniemi", graphBuilder.createGraph(PATH + ROVANIEMI));
        this.graphs.put("helsinki", graphBuilder.createGraph(PATH + HELSINKI));
        this.graphs.put("ivalo", graphBuilder.createGraph(PATH + IVALO));
        this.graphs.put("turku", graphBuilder.createGraph(PATH + TURKU)); */
    }

    private void initializeNodes(GraphBuilder graphBuilder){
        this.nodes.put("tornio", graphBuilder.createNodeMap(PATH + TORNIO));
        /* this.nodes.put("rovaniemi", graphBuilder.createNodeMap(PATH + ROVANIEMI));
        this.nodes.put("helsinki", graphBuilder.createNodeMap(PATH + HELSINKI));
        this.nodes.put("ivalo", graphBuilder.createNodeMap(PATH + IVALO));
        this.nodes.put("turku", graphBuilder.createNodeMap(PATH + TURKU)); */
    }

    public String visualizeAlgorithm(String city, String algorithm, Node start, Node goal){
        if(this.graphs.containsKey(city)){
            Node trueStartNode = findNearestPoint(start, this.nodes.get(city));
            Node trueGoalNode = findNearestPoint(goal, this.nodes.get(city));
            if(algorithm.equals(DIJKSTRA)){
                Dijkstra dijkstra = new Dijkstra();
                long distance = dijkstra.getShortestPath(this.nodes.get(city), this.graphs.get(city), trueStartNode.getId(), trueGoalNode.getId());
                return "" + distance;
            } else if (algorithm.equals(ASTAR)){
                AStar aStar = new AStar();
                return "" + aStar.getShortestPath(this.nodes.get(city),this.graphs.get(city),trueStartNode.getId(),trueGoalNode.getId());
            }
        } else {
            return "Invalid city";
        }
        return "Invalid algorithm";
    }

    public Node findNearestPoint(Node node, Map<Integer, Node> nodes){
        long minDistance = Long.MAX_VALUE;
        Node minNode = null;
        for (Node n:nodes.values()) {
            long distance = new CoordinateDistance().distance(node.getLat(),node.getLon(),n.getLat(),n.getLon());
            if(distance < minDistance){
                minNode = n;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
