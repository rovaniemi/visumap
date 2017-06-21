package visumap.Algorithms;
import java.util.Comparator;

public class AStarComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        AStarNode obj1 = (AStarNode) o1;
        AStarNode obj2 = (AStarNode) o2;
        long compare = (obj1.getToStart() + obj1.getToGoal()) - (obj2.getToStart() + obj2.getToGoal());
        if(compare < 0) return -1;
        if(compare == 0) return 0;
        return 1;
    }
}
