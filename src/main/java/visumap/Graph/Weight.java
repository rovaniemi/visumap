package visumap.Graph;

/**
 * Weight luokka edustaa kaarta ja sen painoa.
 */

public class Weight {

    private int i;
    private int w;

    public Weight(int i, int w) {
        this.i = i;
        this.w = w;
    }

    public int getI() {
        return i;
    }

    public int getW() {
        return w;
    }
}
