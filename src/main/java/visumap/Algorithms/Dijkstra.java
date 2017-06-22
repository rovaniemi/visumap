package visumap.Algorithms;

import visumap.Graph.*;
import visumap.Statistic.Stats;
import visumap.Structures.MinHeap;

public class Dijkstra {

    private Stats stats;
    private long testDist;

    public Dijkstra(Node[] nodes, int start, int goal){
        this.stats = new Stats();
        getShortestPath(nodes, start, goal);
    }

    /**
     * getShortestPath hoitaa algoritmin ja palauttaa lyhyimmän polun.
     * @param goal maalisolmun id.
     * @param start lähtösolmun id.
     * @param nodes nodemap, josta saa latituden ja longituden.
     * @return lyhin reitti senttimetreissä.
     */

    public long getShortestPath(Node[] nodes, int start, int goal){
        boolean[] handled = new boolean[nodes.length + 1];
        int[] dist = new int[nodes.length + 1];
        int[] path = new int[nodes.length + 1];

        for (int i = 0; i <= nodes.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }

        dist[start] = 0;

        MinHeap<Weight> minHeap = new MinHeap(new DijkstraComparator());
        minHeap.add(new Weight(start, 0));

        while(!minHeap.isEmpty() && !handled[goal]){
            Weight weight = minHeap.poll();
            int id = weight.getI();
            int distance = weight.getW();

            if(handled[id]){
                continue;
            } else if (handled[goal]) break;

            handled[id] = true;

            for (Weight next:nodes[id].getE()) {
                if(dist[next.getI()] > distance + next.getW()){
                    dist[next.getI()] = distance + next.getW();
                    path[next.getI()] = id;
                    minHeap.add(new Weight(next.getI(),dist[next.getI()]));
                }
            }
        }

        if (dist[goal] == Integer.MAX_VALUE){
            this.testDist = -1;
            return -1;
        } else this.testDist = dist[goal];

        this.stats.shortestPath(nodes,path,start,goal);
        this.stats.createStats();
        new PathConverter().convertPathToPaths(nodes,path,stats);
        return this.stats.getDistance();
    }

    public Stats getStats(){
        return this.stats;
    }

    public long getTestDist() {
        return testDist;
    }
}