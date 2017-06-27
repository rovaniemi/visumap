package visumap.Algorithms;
import visumap.Tools.CoordinateDistance;

import java.util.Comparator;

public class AStarComparator implements Comparator{

    private Double gLat;
    private Double gLon;
    private CoordinateDistance coordinateDistance;

    /**
     * AStarComparator luokka hoitaa astarille aina parhaan alkion käsittelyyn.
     * Toisinsanoen luokka laskee AStarin heurestikka funktion ja vertailee kahta nodea keskenään.
     * Algoritmi tallentaa suorituksen aikana tietoja stats luokkaan.
     * @param gLat maalisolmun latitude.
     * @param gLon maalisolmun longitude.
     */

    public AStarComparator(double gLat, double gLon){
        this.gLat = gLat;
        this.gLon = gLon;
        this.coordinateDistance = new CoordinateDistance();
    }

    /**
     * Compare metodissa lasketaan Astarin heurestikkaa funktio viimeiseen pisteeseen.
     * Kun hoidamme laskemisen täällä, niin meidän ei tarvi laskea jokaisen solmun pituutta lopetussolmuun.
     * @param o1 ensimmäinen astarNode.
     * @param o2 toinen astarNode.
     * @return kumpi on pienempi.
     */

    @Override
    public int compare(Object o1, Object o2) {
        AStarNode obj1 = (AStarNode) o1;
        AStarNode obj2 = (AStarNode) o2;
        if(obj1.getToGoal() == -1){
            obj1.setToGoal(this.coordinateDistance.distance(obj1.getLat(),obj1.getLon(),gLat,gLon));
        }
        if(obj2.getToGoal() == -1){
            obj2.setToGoal(this.coordinateDistance.distance(obj2.getLat(),obj2.getLon(),gLat,gLon));
        }
        long compare = (obj1.getToStart() + obj1.getToGoal()) - (obj2.getToStart() + obj2.getToGoal());
        if(compare < 0) return -1;
        if(compare == 0) return 0;
        return 1;
    }
}
