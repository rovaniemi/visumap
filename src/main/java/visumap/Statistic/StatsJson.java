package visumap.Statistic;

import visumap.Graph.Node;

import java.util.List;

public class StatsJson {

    private List<Node> everyNode;
    private List<Node> shortestPath;
    private String message;
    private long distance;
    private int size;

    public StatsJson(List<Node> everyNode, List<Node> shortestPath, String message, long distance, int size){
        this.everyNode = everyNode;
        this.shortestPath = shortestPath;
        this.message = message;
        this.distance = distance;
        this.size = size;
    }
}
