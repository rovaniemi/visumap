package visumap.Statistic;

import visumap.Graph.Node;

import java.util.List;

/**
 * Kerääjä kaikelle datalle mitä json tiedostoon halutaan.
 */

public class StatsJson {

    public long time;
    private int size;
    public long distance;
    private String message;
    public List<NodeJson> shortestPath;
    private List<NodeJson> everyNode;

    public StatsJson(long time, int size, long distance, String message, List<NodeJson> shortestPath, List<NodeJson> everyNode ){
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
