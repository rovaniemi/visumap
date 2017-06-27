package visumap.Algorithms;

public class AStarNode {

    private int id;
    private long toStart;
    private double lat;
    private double lng;
    private int toGoal;

    /**
     * AstarNode luokka auttaa Astar luokkaa.
     * AStarNodella on tieto omasta sijainnista, ja matkasta alkusolmuun.
     * @param id noden id.
     * @param toStart matka alkuun.
     * @param lat noden lat.
     * @param lng noden lng.
     */

    public AStarNode (int id, int toStart, double lat, double lng) {
        this.id = id;
        this.toStart = toStart;
        this.lat = lat;
        this.lng = lng;
        this.toGoal = -1;
    }

    public int getId() {
        return id;
    }

    public long getToStart() {
        return toStart;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lng;
    }

    public int getToGoal() {
        return toGoal;
    }

    public void setToGoal(int toGoal) {
        this.toGoal = toGoal;
    }
}