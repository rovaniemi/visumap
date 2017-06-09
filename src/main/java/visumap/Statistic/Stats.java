package visumap.Statistic;

import visumap.Graph.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Stats luokka on statistiikan luomista varten.
 */

public class Stats {

    private long startTime;
    private boolean on;
    private List<Node> everyNode;
    private List<Node> shortestPath;
    private int nodeCounter;
    private final TimeSupplier timeSupplier;

    public Stats(TimeSupplier timeSupplier){
        this.everyNode = new ArrayList<>();
        this.shortestPath = new ArrayList<>();
        this.nodeCounter = 0;
        this.timeSupplier = timeSupplier;
    }

    public Stats(){
        this(new SystemTimeSupplier());
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

    public int getNodeCounter(){
        return this.nodeCounter;
    }

    public long nanosecondsToMilliseconds(Long nanoseconds) {
        return nanoseconds / 1000000;
    }
}
