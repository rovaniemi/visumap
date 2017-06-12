package visumap.Algorithms;

import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Graph.WeightComparator;
import visumap.Statistic.Stats;
import visumap.Structures.MinHeap;

import java.util.List;
import java.util.Map;

public class Dijkstra implements ShortestPathAlgorithm {

    private Stats stats;

    public Dijkstra(){
        this.stats = new Stats();
    }

    public long getShortestPath(Map<Integer, Node> nodes, List<Weight>[] graph, int start, int goal){
        boolean[] handled = new boolean[graph.length + 1];
        long[] dist = new long[graph.length + 1];
        int[] path = new int[graph.length + 1];

        for (int i = 0; i <= graph.length; i++) {
            dist[i] = Long.MAX_VALUE;
            path[i] = -1;
        }

        dist[start] = 0;

        MinHeap<Weight> minHeap = new MinHeap(new WeightComparator());
        minHeap.add(new Weight(start, 0l));

        while(!minHeap.isEmpty()){
            Weight weight = minHeap.poll();
            int id = weight.getId();
            long distance = weight.getWeight();

            if(handled[id]){
                continue;
            }

            handled[id] = true;

            for (Weight next:graph[id]) {
                if(dist[next.getId()] > distance + next.getWeight()){
                    dist[next.getId()] = distance + next.getWeight();
                    minHeap.add(new Weight(next.getId(),dist[next.getId()]));
                }
            }
        }

        if (dist[goal] == Long.MAX_VALUE){
            return -1;
        }
        return dist[goal];
    }

    public Stats getStats(){
        return this.stats;
    }
}
