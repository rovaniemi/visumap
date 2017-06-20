package visumap.Statistic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import visumap.Graph.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Stats luokka on statistiikan luomista varten.
 */

public class Stats {

    private long startTime;
    private List<Node> everyNode;
    private List<Node> shortestPath;
    private String message;
    private long distance;


    public Stats(String message){
        this.everyNode = new ArrayList<>();
        this.shortestPath = new ArrayList<>();
        this.message = message;
        this.startTime = System.currentTimeMillis();
        this.distance = -1;
    }

    public Stats(){
        this("");
    }

    public void addNodeE(Node node){
        this.everyNode.add(node);
    }

    public void addNodeS(Node node){
        this.shortestPath.add(node);
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDistance() {
        return distance;
    }

    public List<Node> getEveryNode(){
        shortestPath.stream().forEach(e -> this.everyNode.remove(e));
        return this.everyNode;
    }

    public String getJson(){
        long time = System.currentTimeMillis() - this.startTime;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<NodeJson> path = new ArrayList<>();
        for (int i = 0; i < this.shortestPath.size(); i++) {
            path.add(new NodeJson(this.shortestPath.get(i).getLa(),this.shortestPath.get(i).getLo()));
        }
        return gson.toJson(new StatsJson(time,this.everyNode.size() + this.shortestPath.size(), distance, this.message, path, this.everyNode )).toString();
    }
}
