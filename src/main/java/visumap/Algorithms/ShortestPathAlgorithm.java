package visumap.Algorithms;

import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Statistic.Stats;

/**
 * Interface lyhimmän polun etsintä algoritmeille.
 */

public interface ShortestPathAlgorithm {
    public Stats getStats();
    public long getShortestPath(Node[] nodes, int start, int goal);
}
