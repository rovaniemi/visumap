package visumap.Graph;

/**
 * Node on verkon yksi solmu. Nodella on latitude ja longitude.
 */

public class Node {

    private double la;
    private double lo;
    private Weight[] e;

    public Node(double la, double lo, Weight[] e) {
        this.la = la;
        this.lo = lo;
        this.e = e;
    }

    public double getLa() {
        return la;
    }

    public double getLo() {
        return lo;
    }

    public Weight[] getE() {
        return e;
    }
}