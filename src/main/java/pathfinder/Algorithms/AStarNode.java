package pathfinder.Algorithms;

public class AStarNode implements Comparable<AStarNode>{

    private Integer id;
    private long toGoal;
    private long toStart;

    public AStarNode (int id, long toStart, long toGoal) {
        this.id = id;
        this.toStart = toStart;
        this.toGoal = toGoal;
    }

    @Override
    public int compareTo(AStarNode o) {
        return (int)((toStart + toGoal) - (o.toStart - o.toGoal));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AStarNode aStarNode = (AStarNode) o;
        return id.equals(aStarNode.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}