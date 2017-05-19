package pathfinder.Graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private long id;
    private double lat;
    private double lon;
    private List<Node> nodes;

    public Node(long id, double lat, double lon){
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.nodes = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
