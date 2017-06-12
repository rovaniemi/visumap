package visumap.Algorithms;

import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Statistic.Stats;
import visumap.Structures.MinHeap;
import visumap.Tools.CoordinateDistance;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Astar luokka hoitaa getShortestPath algoritmin lyhyimmän polun etsinnän.
 */

public class AStar implements ShortestPathAlgorithm{

    private CoordinateDistance tool;
    private Stats stats;

    public AStar(){
        this.tool = new CoordinateDistance();
        this.stats = new Stats();
    }

    /**
     * Astar hoitaa algoritmin ja palauttaa lyhyimmän polun.
     * @param goal maalisolmun id.
     * @param start lähtösolmun id.
     * @param graph vieruslista.
     * @param nodes nodemap, josta saa latituden ja longituden.
     * @return lyhin reitti senttimetreissä.
     */

    public long getShortestPath(Map<Integer, Node> nodes, List<Weight>[] graph, int start, int goal){
        if(start == goal){
            return 0;
        }

        long[] toStart = new long[graph.length];
        long[] toGoal = new long[graph.length];
        int[] path = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            toStart[i] = Integer.MAX_VALUE - (Integer.MAX_VALUE / 10);
            toGoal[i] = tool.distance(nodes.get(i).getLat(),nodes.get(i).getLon(),nodes.get(goal).getLat(),nodes.get(goal).getLon());
            path[i] = -1;
        }
        toStart[start] = 0;
        MinHeap<AStarNode> minHeap = new MinHeap<>(new AStarNodeComparator());
        for (int i = 0; i < graph.length; i++) {
            minHeap.add(new AStarNode(i,toStart[i],toGoal[i]));
        }

        Set<Integer> set = new HashSet<>();
        while(!set.contains(goal)){
            AStarNode node = minHeap.poll();
            int nodeid = node.getId();
            this.stats.addNodeE(nodes.get(nodeid));
            set.add(nodeid);
            for (int i = 0; i < graph[nodeid].size(); i++) {
                Weight nextNode = graph[nodeid].get(i);
                if(toStart[nextNode.getId()] > toStart[nodeid] + nextNode.getWeight()){
                    toStart[nextNode.getId()] = toStart[nodeid] + nextNode.getWeight();
                    minHeap.add(new AStarNode(nextNode.getId(), toStart[nextNode.getId()],toGoal[nextNode.getId()]));
                    path[nextNode.getId()] = nodeid;
                }
            }
        }
        long shortestPath = shortestPath(nodes, path, start, goal);
        return shortestPath;
    }

    /**
     * ShortestPath hoitaa path taulukosta lopullisen pituuden laskemisen.
     * @param goal maalisolmun id.
     * @param start lähtösolmun id.
     * @param nodes nodemap, josta saa latituden ja longituden.
     * @return lyhin reitti senttimetreissä.
     */

    private long shortestPath(Map<Integer, Node> nodes, int[] path, int start, int goal){
        CoordinateDistance tool = new CoordinateDistance();
        long distance = 0;
        int next = goal;

        while(true){
            if(path[next] == -1){
                return -1;
            }
            distance += tool.distance(nodes.get(next).getLat(),nodes.get(next).getLon(),nodes.get(path[next]).getLat(),nodes.get(path[next]).getLon());
            this.stats.addNodeS(nodes.get(next));
            next = path[next];
            if(next == start) {
                return distance;
            }
        }
    }

    public Stats getStats(){
        return this.stats;
    }
}
