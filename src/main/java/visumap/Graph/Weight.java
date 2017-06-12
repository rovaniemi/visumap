package visumap.Graph;

public class Weight{

    /**
     * Weight -luokka esittää verkon solmujen välisiä painoja.
     */

    private Integer id;
    private Long weight;

    /**
     * Weight luokalle määritellään aina id, sekä paino.
     * @param id solmun yksilöllinen tunnus.
     * @param weight solmujen välinen etäisyys.
     */

    public Weight(int id, long weight) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weight w = (Weight) o;
        if (this.id.intValue() != w.id.intValue()) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id.intValue();
    }

    public long getWeight() {
        return weight;
    }
}
