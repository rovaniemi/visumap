package visumap.Statistic;

import visumap.Graph.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Stats luokka on statistiikan luomista varten.
 */

public class Stats {

    private List<Node> everyNode;
    private List<Node> shortestPath;
    private int nodeCounter;
    private int runningTime;
    private SystemTimeSupplier timeSupplier;

    public Stats(){
        this.everyNode = new ArrayList<>();
        this.shortestPath = new ArrayList<>();
        this.runningTime = 0;
        this.nodeCounter = 0;
        this.timeSupplier = new SystemTimeSupplier();
    }

    public void addNodeE(Node node){
        nodeCounter++;
        this.everyNode.add(node);
    }

    public void addNodeS(Node node){
        this.shortestPath.add(node);
    }

    public List<Node> getEveryNode(){
        shortestPath.stream().forEach(e -> this.everyNode.remove(e));
        return this.everyNode;
    }

    public List<Node> getShortestPath(){
        return this.shortestPath;
    }

    public SystemTimeSupplier getTimeSupplier(){
        return this.timeSupplier;
    }

    public void addRunningTime(int runningTime){
        this.runningTime = runningTime;
    }

    public int getRunningTime(){
        return this.runningTime;
    }

    public int getNodeCounter(){
        return this.nodeCounter;
    }
}
