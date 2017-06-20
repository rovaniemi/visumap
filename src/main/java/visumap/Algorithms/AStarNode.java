package visumap.Algorithms;

public class AStarNode {

    private int id;
    private long toStart;
    private long toGoal;

    public AStarNode (int id, int toStart, int toGoal) {
        this.id = id;
        this.toStart = toStart;
        this.toGoal = toGoal;
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