package visumap.Algorithms;

import visumap.Graph.Node;
import visumap.Statistic.Stats;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PathConverter {

    private HashSet<Integer> first = new HashSet<>();

    public void convertPathArrayToCircle(Node[] nodes, int[] path, Stats stats) {
        createSet(path);
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < path.length; i++) {
            if(!first.contains(i) && path[i] != -1){
                nodeList.add(nodes[i]);
            }
        }
        List<Node> quickHull = new QuickHull().quickHull(nodeList);
        stats.setEveryNode(quickHull);
    }

    public void createSet(int[] path){
        for (int i = 0; i < path.length; i++) {
            if(path[i] != -1){
                first.add(path[i]);
            }
        }
    }
}
