package visumap.Algorithms;

public class AStarNode implements Comparable<AStarNode>{

    private int id;
    private long toStart;
    private long toGoal;

    public AStarNode (int id, long toStart, long toGoal) {
        this.id = id;
        this.toStart = toStart;
        this.toGoal = toGoal;
    }

    @Override
    public int compareTo(AStarNode o) {
        return (int)((toStart + toGoal) - (o.toStart + o.toGoal));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return id == ((AStarNode) o).getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public long getToStart() {
        return toStart;
    }

    public long getToGoal() {
        return toGoal;
    }
}