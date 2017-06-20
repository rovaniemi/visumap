package visumap.Graph;

public class Node2{

    private double la;
    private double lo;
    private Weight2[] e;

    public Node2(double la, double lo, Weight2[] e) {
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

    public Weight2[] getE() {
        return e;
    }
}