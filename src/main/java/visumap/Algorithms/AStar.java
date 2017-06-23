package visumap.Algorithms;

import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Statistic.Stats;
import visumap.Structures.MinHeap;
import visumap.Tools.CoordinateDistance;


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
        int[] path = new int[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            toStart[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }

        toStart[start] = 0;

        MinHeap<AStarNode> minHeap = new MinHeap<>(nodes.length, new AStarComparator(nodes[goal].getLa(), nodes[goal].getLo()));
        minHeap.add(new AStarNode(start,toStart[start],nodes[start].getLa(),nodes[start].getLo()));

        boolean[] handled = new boolean[nodes.length];
        while(!minHeap.isEmpty()){
            AStarNode node = minHeap.poll();
            int nodeid = node.getId();
            if(handled[goal]) break;
            if(handled[nodeid]) continue;
            handled[nodeid] = true;
            for (int i = 0; i < nodes[nodeid].getE().length; i++) {
                Weight nextNode = nodes[nodeid].getE()[i];
                if(toStart[nextNode.getI()] > toStart[nodeid] + nextNode.getW()){
                    toStart[nextNode.getI()] = toStart[nodeid] + nextNode.getW();
                    minHeap.add(new AStarNode(nextNode.getI(), toStart[nextNode.getI()], nodes[nextNode.getI()].getLa(), nodes[nextNode.getI()].getLo()));
                    path[nextNode.getI()] = nodeid;
                }
            }
        }
        this.stats.createStats(nodes,path,start,goal,handled);
        return this.stats.getDistance();
    }

    public Stats getStats(){
        return this.stats;
    }
}
