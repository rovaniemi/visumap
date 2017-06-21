package visumap.Statistic;

import visumap.Graph.Node;

import java.util.List;

/**
 * Collectori kaikelle datalle mitä json tiedostoon halutaan.
 */

public class StatsJson {

    private long time;
    private int size;
    private long distance;
    private String message;
    private List<NodeJson> shortestPath;
    private List<Node> everyNode;

    public StatsJson(long time, int size, long distance, String message, List<NodeJson> shortestPath, List<Node> everyNode ){
        this.time = time;
        this.size = size;
        this.distance = distance;
        this.message = message;
        this.shortestPath = shortestPath;
        this.everyNode = everyNode;
    }

    public long getDistance() {
        return distance;
    }

    public String getMessage() {
        return message;
    }
}
