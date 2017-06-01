package pathfinder.Algorithms;

import pathfinder.Graph.Weight;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public Dijkstra(){

    }

    public long dijkstra(List<Weight>[] graph, int start, int goal){
        boolean[] handled = new boolean[graph.length + 1];
        long[] dist = initialiseDistance(start, graph.length);
        PriorityQueue<Weight> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Weight(start,0l));

        while(!priorityQueue.isEmpty()){
            Weight weight = priorityQueue.poll();
            int id = weight.getId();
            long distance = weight.getWeight();

            if(handled[id]){
                continue;
            }

            handled[id] = true;

            for (Weight next:graph[id]) {
                if(dist[next.getId()] > distance + next.getWeight()){
                    dist[next.getId()] = distance + next.getWeight();
                    priorityQueue.add(new Weight(next.getId(),dist[next.getId()]));
                }
            }
        }

        if (dist[goal] == Long.MAX_VALUE){
            return -1;
        }
        return dist[goal];
    }

    public long[] initialiseDistance(int start, int length){
        long[] dist = new long[length + 1];
        for (int i = 1; i <= length; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        dist[start] = 0;
        return dist;
    }
}
