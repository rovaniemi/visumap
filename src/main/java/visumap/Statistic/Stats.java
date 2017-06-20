package visumap.Statistic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import visumap.Graph.Node;
import visumap.Graph.Node2;
import visumap.Tools.CoordinateDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * Stats luokka on statistiikan luomista varten.
 */

public class Stats {

    private long startTime;
    private boolean on;
    private List<Node> everyNode;
    private List<Node2> shortestPath;
    private String message;
    private final TimeSupplier timeSupplier;

    public Stats(TimeSupplier timeSupplier, String message){
        this.everyNode = new ArrayList<>();
        this.shortestPath = new ArrayList<>();
        this.timeSupplier = timeSupplier;
        this.message = message;
    }

    public Stats(){
        this(new SystemTimeSupplier(), "");
    }

    public Stats(String message){
        this(new SystemTimeSupplier(), message);
    }

    public void addNodeE(Node node){
        this.everyNode.add(node);
    }

    public void addNodeS(Node2 node){
        this.shortestPath.add(node);
    }

    public List<Node> getEveryNode(){
        shortestPath.stream().forEach(e -> this.everyNode.remove(e));
        return this.everyNode;
    }

    public List<Node2> getShortestPath(){
        return this.shortestPath;
    }

    public void startTimeTracking() {
        if (!this.on) {
            this.startTime = timeSupplier.getNanoseconds();
            this.on = true;
        }
    }

    public long stopTimeTracking() {
        long runningTime = 0;
        if (this.on) {
            runningTime = nanosecondsToMilliseconds(timeSupplier.getNanoseconds() - this.startTime);
            this.on = false;
        }
        return runningTime;
    }


    public long nanosecondsToMilliseconds(Long nanoseconds) {
        return nanoseconds / 1000000;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long shortestPath(){
        CoordinateDistance tool = new CoordinateDistance();
        long distance = 0;
        for (int i = 0; i < this.getShortestPath().size(); i++) {
            if(i < this.getShortestPath().size() - 1){
                Node2 n1 = this.getShortestPath().get(i);
                Node2 n2 = this.getShortestPath().get(i + 1);
                distance += tool.distance(n1.getLa(), n1.getLo(), n2.getLa(), n2.getLo());
            }
        }
        return distance;
    }

    public String getJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<NodeJson> path = new ArrayList<>();
        for (int i = 0; i < this.shortestPath.size(); i++) {
            path.add(new NodeJson(this.shortestPath.get(i).getLa(),this.shortestPath.get(i).getLo()));
        }
        return gson.toJson(new StatsJson(this.everyNode,path,this.message,shortestPath(),this.everyNode.size() + this.shortestPath.size())).toString();
    }
}
