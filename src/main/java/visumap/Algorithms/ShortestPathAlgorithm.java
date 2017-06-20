package visumap.Algorithms;

import visumap.Graph.Node2;
import visumap.Graph.Weight;
import visumap.Statistic.Stats;

import java.util.List;

/**
 * Interface lyhimmän polun etsintä algoritmeille.
 */

public interface ShortestPathAlgorithm {
    public Stats getStats();
    public long getShortestPath(Node2[] nodes, int start, int goal);
}
