package pathfinder;

import pathfinder.Graph.GraphBuilder;
import pathfinder.Graph.Node;
import pathfinder.Graph.Weight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    private static final String TORNIO = "tornio-pretty.json";
    private static final String ROVANIEMI = "rovaniemi.json";
    private static final String HELSINKI = "helsinki.json";
    private static final String IVALO = "ivalo.json";
    private static final String TURKU = "turku.json";
    private static final String PATH = "maps/";

    private Map<String, List<Weight>[]> graphs;

    public Router(){
        GraphBuilder graphBuilder = new GraphBuilder();
        this.graphs = new HashMap<>();
        this.graphs.put("tornio", graphBuilder.greateGraph(PATH + TORNIO));
        /* this.graphs.put("rovaniemi", graphBuilder.greateGraph(PATH + ROVANIEMI));
        this.graphs.put("helsinki", graphBuilder.greateGraph(PATH + HELSINKI));
        this.graphs.put("ivalo", graphBuilder.greateGraph(PATH + IVALO));
        this.graphs.put("turku", graphBuilder.greateGraph(PATH + TURKU)); */
    }

    private String visualizeAlgorithm(){
        return "";
    }

    public Node findNearestPoint(double lat, double lon, Map<Long, Node> nodes){
        final Node[] min = new Node[1];
        final long[] minDistance = new long[1];
        minDistance[0] = Long.MAX_VALUE;
        nodes.values().stream().forEach(n -> {
            long distance = distance(lat, lon, n.getLat(), n.getLon());
            if(distance < minDistance[0]){
                minDistance[0] = distance;
                min[0] = n;
            }
        });
        return min[0];
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
