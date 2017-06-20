package visumap.Algorithms;
import visumap.Graph.Weight;

import java.util.Comparator;

/**
 * DijkstraComparator luokka hoitaa kahden weightin vertailun keskenään.
 */

public class DijkstraComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Weight obj1 = (Weight) o1;
        Weight obj2 = (Weight) o2;
        if(obj1.getW() == obj2.getW()) return obj1.getI() - obj2.getI();
        return  obj1.getW() - obj2.getW();
    }
}