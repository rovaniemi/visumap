package pathfinder.Algorithms;

public class AStarNode implements Comparable<AStarNode>{

    private int id;
    private long toGoal;
    private long toStart;

    public AStarNode (int id, long toStart, long toGoal) {
        this.id = id;
        this.toStart = toStart;
        this.toGoal = toGoal;
    }

    @Override
    public int compareTo(AStarNode o) {
        return (int)((o.toStart - o.toGoal) - (toStart + toGoal));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id == ((AStarNode) o).getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }
}