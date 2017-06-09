package visumap.Algorithms;

import java.util.Comparator;

public class AStarNodeComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        AStarNode obj1 = (AStarNode) o1;
        AStarNode obj2 = (AStarNode) o2;
        return (int)((obj1.getToStart() + obj1.getToGoal()) - (obj2.getToStart() + obj2.getToGoal()));
    }
}
