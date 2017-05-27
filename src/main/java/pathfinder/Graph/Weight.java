package pathfinder.Graph;

public class Weight {

    /**
     * Weight luokka esittää verkon solmujen välisiä painoja.
     */

    private long id;
    private long weight;

    /**
     * Weight luokalle määritellään aina id, sekä paino.
     * @param id solmun yksilöllinen tunnus.
     * @param weight solmujen välinen etäisyys.
     */

    public Weight(long id, long weight) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weight w = (Weight) o;
        if (this.id != w.id) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public long getWeight() {
        return weight;
    }
}
