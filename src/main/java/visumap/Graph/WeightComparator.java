package visumap.Graph;
import java.util.Comparator;

/**
 * WeightComparator luokka hoitaa kahden weightin vertailun keskenään.
 */

public class WeightComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Weight obj1 = (Weight) o1;
        Weight obj2 = (Weight) o2;
        if(obj1.getWeight() == obj2.getWeight()){
            return obj1.getId() - obj2.getId();
        }
        return (int) (obj1.getWeight() - obj2.getWeight());
    }
}
