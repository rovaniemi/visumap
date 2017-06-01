package pathfinder;

import pathfinder.Algorithms.Dijkstra;
import pathfinder.Graph.GraphBuilder;
import pathfinder.Graph.Node;
import pathfinder.Graph.Weight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    private static final String DIJKSTRA = "dijkstra";
    private static final String ASTAR = "astar";
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
        this.graphs.put("tornio", graphBuilder.greateGraph(PATH + TORNIO));
        /* this.graphs.put("rovaniemi", graphBuilder.greateGraph(PATH + ROVANIEMI));
        this.graphs.put("helsinki", graphBuilder.greateGraph(PATH + HELSINKI));
        this.graphs.put("ivalo", graphBuilder.greateGraph(PATH + IVALO));
        this.graphs.put("turku", graphBuilder.greateGraph(PATH + TURKU)); */
    }

    private void initializeNodes(GraphBuilder graphBuilder){
        this.nodes.put("tornio", graphBuilder.greateNodeMap(PATH + TORNIO));
        /* this.nodes.put("rovaniemi", graphBuilder.greateNodeMap(PATH + ROVANIEMI));
        this.nodes.put("helsinki", graphBuilder.greateNodeMap(PATH + HELSINKI));
        this.nodes.put("ivalo", graphBuilder.greateNodeMap(PATH + IVALO));
        this.nodes.put("turku", graphBuilder.greateNodeMap(PATH + TURKU)); */
    }

    public String visualizeAlgorithm(String city, String algorithm, Node start, Node goal){
        if(this.graphs.containsKey(city)){
            Node trueStartNode = findNearestPoint(start, this.nodes.get(city));
            Node trueGoalNode = findNearestPoint(goal, this.nodes.get(city));
            if(algorithm.equals(DIJKSTRA)){
                Dijkstra dijkstra = new Dijkstra();
                long distance = dijkstra.dijkstra(this.graphs.get(city), trueStartNode.getId(),trueGoalNode.getId());
                return "" + distance;
            } else if (algorithm.equals(ASTAR)){
                // Astar
            }
        } else {
            return "Invalid city";
        }
        return "";
    }

    public Node findNearestPoint(Node node, Map<Integer, Node> nodes){
        long minDistance = Long.MAX_VALUE;
        Node minNode = null;
        for (Node n:nodes.values()) {
            long distance = distance(node.getLat(),node.getLon(),n.getLat(),n.getLon());
            if(distance < minDistance){
                minNode = n;
                minDistance = distance;
            }
        }
        return minNode;
    }

    private long distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist *= 60 * 1.1515 * 1.609344 * 100000;
        return (int) (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
