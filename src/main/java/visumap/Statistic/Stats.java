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
        this.distance = 0;
    }

    public Stats(){
        this("");
    }

    public void setEveryNode(List<Node> everyNode) {
        this.everyNode = everyNode;
    }

    public long getDistance() {
        return distance;
    }

    public List<Node> getEveryNode(){
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
        if(this.shortestPath.isEmpty()) distance = 0;
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
        Gson gson = new GsonBuilder().create();
        List<NodeJson> path = new ArrayList<>();
        for (int i = 0; i < this.shortestPath.size(); i++) {
            path.add(new NodeJson((float)this.shortestPath.get(i).getLa(),(float)this.shortestPath.get(i).getLo()));
        }
        List<NodeJson> ePath = new ArrayList<>();
        for (int i = 0; i < this.everyNode.size(); i++) {
            ePath.add(new NodeJson((float)this.everyNode.get(i).getLa(),(float)this.everyNode.get(i).getLo()));
        }
        return gson.toJson(new StatsJson(time, this.shortestPath.size(), distance, this.message, path, ePath)).toString();
    }
}
