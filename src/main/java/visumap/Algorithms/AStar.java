package visumap.Algorithms;

import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Statistic.Stats;
import visumap.Structures.MinHeap;
import visumap.Tools.CoordinateDistance;

import java.util.HashSet;
import java.util.Set;


/**
 * Astar luokka hoitaa getShortestPath algoritmin lyhyimmän polun etsinnän.
 */

public class AStar implements ShortestPathAlgorithm{

    private CoordinateDistance tool;
    private Stats stats;

    public AStar(Node[] nodes, int start, int goal){
        this.tool = new CoordinateDistance();
        this.stats = new Stats();
        getShortestPath(nodes, start, goal);
    }

    /**
     * getShortestPath hoitaa algoritmin ja palauttaa lyhyimmän polun.
     * @param goal maalisolmun id.
     * @param start lähtösolmun id.
     * @param graph vieruslista.
     * @param nodes nodemap, josta saa latituden ja longituden.
     * @return lyhin reitti senttimetreissä.
     */

    public long getShortestPath(Node[] nodes, int start, int goal){

        if(start == goal) return 0;

        int[] toStart = new int[nodes.length];
        int[] toGoal = new int[nodes.length];
        int[] path = new int[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            toStart[i] = Integer.MAX_VALUE;
            toGoal[i] = tool.distance(nodes[i].getLa(), nodes[i].getLo(), nodes[goal].getLa(), nodes[goal].getLo());
            path[i] = -1;
        }

        toStart[start] = 0;

        MinHeap<AStarNode> minHeap = new MinHeap<>(nodes.length, new AStarComparator());

        for (int i = 0; i < nodes.length; i++) {
            minHeap.add(new AStarNode(i,toStart[i],toGoal[i]));
        }

        boolean[] set = new boolean[nodes.length];
        while(!set[goal]){
            AStarNode node = minHeap.poll();
            int nodeid = node.getId();
            set[nodeid] = true;
            for (int i = 0; i < nodes[nodeid].getE().length; i++) {
                Weight nextNode = nodes[nodeid].getE()[i];
                if(toStart[nextNode.getI()] > toStart[nodeid] + nextNode.getW()){
                    toStart[nextNode.getI()] = toStart[nodeid] + nextNode.getW();
                    minHeap.add(new AStarNode(nextNode.getI(), toStart[nextNode.getI()],toGoal[nextNode.getI()]));
                    path[nextNode.getI()] = nodeid;
                }
            }
        }

        this.stats.shortestPath(nodes, path, start, goal);
        this.stats.createStats();
        return this.stats.getDistance();
    }

    public Stats getStats(){
        return this.stats;
    }
}
