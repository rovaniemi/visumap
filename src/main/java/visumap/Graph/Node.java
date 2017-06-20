package visumap.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Node -luokka esittää yhtä verkon solmua. Solmulla on myös koordinaatit sen paikasta reaalimaailmassa.
 */

public class Node{

    private int id;
    private double lat;
    private double lon;
    private Set<Weight> edges = new HashSet<>();

    /**
     * Node -luokalle annettaan id, lat ja lon.
     * @param id yksilöllinen tunnus.
     * @param lat latitude arvo koordinaatistoon.
     * @param lon longitude arvo koordinaatistoon.
     */


    public Node(int id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public boolean hasEdges(){
        if(this.edges == null) return false;
        return !edges.isEmpty();
    }

    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Node node = (Node) o;
        if (id != node.id){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    public Set<Weight> getEdges() {
        return edges;
    }

    @Override
    public String toString(){
        return "" + this.getId();
    }
}