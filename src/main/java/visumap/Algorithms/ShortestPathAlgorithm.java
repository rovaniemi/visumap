package visumap.Algorithms;

import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Statistic.Stats;

import java.util.List;
import java.util.Map;

public interface ShortestPathAlgorithm {
    public Stats getStats();
    public long getShortestPath(Map<Integer, Node> nodes, List<Weight>[] graph, int start, int goal);
}
