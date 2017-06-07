package pathfinder.Algorithms;

import pathfinder.Graph.Node;
import pathfinder.Graph.Weight;
import pathfinder.Tools.CoordinateDistance;

import java.util.*;

/**
 * Astar luokka hoitaa astar algoritmin lyhyimmän polun etsinnän.
 */

public class AStar {

    private CoordinateDistance tool;

    public AStar(){
        this.tool = new CoordinateDistance();
    }

    /**
     * Astar hoitaa algoritmin ja palauttaa lyhyimmän polun.
     * @param goal maalisolmun id.
     * @param start lähtösolmun id.
     * @param graph vieruslista.
     * @param nodes nodemap, josta saa latituden ja longituden.
     * @return lyhin reitti senttimetreissä.
     */

    public long astar(Map<Integer, Node> nodes, List<Weight>[] graph, int start, int goal){
        long[] toStart = new long[graph.length];
        long[] toGoal = new long[graph.length];
        int[] path = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            toStart[i] = Integer.MAX_VALUE - (Integer.MAX_VALUE / 10);
            toGoal[i] = tool.distance(nodes.get(i).getLat(),nodes.get(i).getLon(),nodes.get(goal).getLat(),nodes.get(goal).getLon());
            path[i] = -1;
        }
        toStart[start] = 0;

        PriorityQueue<AStarNode> priorityQueue = new PriorityQueue<>(AStarNode::compareTo);
        for (int i = 0; i < graph.length; i++) {
            priorityQueue.add(new AStarNode(i,toStart[i],toGoal[i]));
        }

        Set<Integer> set = new HashSet<>();
        while(!set.contains(goal)){
            AStarNode node = priorityQueue.poll();
            int nodeid = node.getId();
            set.add(nodeid);
            for (int i = 0; i < graph[nodeid].size(); i++) {
                Weight nextNode = graph[nodeid].get(i);
                if(toStart[nextNode.getId()] > toStart[nodeid] + nextNode.getWeight()){
                    toStart[nextNode.getId()] = toStart[nodeid] + nextNode.getWeight();
                    priorityQueue.add(new AStarNode(nextNode.getId(), toStart[nextNode.getId()],toGoal[nextNode.getId()]));
                    path[nextNode.getId()] = nodeid;
                }
            }
        }
        return shortestPath(nodes, path, start, goal);
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
            next = path[next];
            if(next == start) {
                return distance;
            }
        }
    }
}
