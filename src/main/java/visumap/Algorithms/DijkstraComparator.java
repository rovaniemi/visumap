package visumap.Algorithms;
import visumap.Graph.Weight2;

import java.util.Comparator;

/**
 * DijkstraComparator luokka hoitaa kahden weightin vertailun keskenään.
 */

public class DijkstraComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Weight2 obj1 = (Weight2) o1;
        Weight2 obj2 = (Weight2) o2;
        if(obj1.getW() == obj2.getW()) return obj1.getI() - obj2.getI();
        return  obj1.getW() - obj2.getW();
    }
}