package visumap.Statistic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import visumap.Graph.Node;
import visumap.Tools.CoordinateDistance;

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

    public void shortestPath(Node[] nodes, int[] path, int start, int goal){
        int next = goal;
        while(true){
            if(path[next] == -1){
                this.distance = -1;
                return;
            }
            this.shortestPath.add(nodes[next]);
            next = path[next];
            if(next == start) {
                return;
            }
        }
    }

    public void createStats(){
        createDistance();
    }

    public void createDistance(){
        if(distance == -1) return;
        CoordinateDistance coordinateDistance = new CoordinateDistance();
        for (int i = 1; i < this.shortestPath.size(); i++) {
            Node a = this.shortestPath.get(i - 1);
            Node b = this.shortestPath.get(i);
            distance += coordinateDistance.distance(a.getLa(),a.getLo(),b.getLa(),b.getLo());
        }
        // change distance cm -> m
        distance = distance / 100;
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
