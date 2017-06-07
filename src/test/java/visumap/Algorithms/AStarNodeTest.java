package visumap.Algorithms;

import org.junit.*;
import visumap.Graph.Node;
import visumap.Tools.CoordinateDistance;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AStarNodeTest {

    private Map<Integer, Node> map;
    private List<Integer>[] graph;

    public AStarNodeTest(){
        this.graph = new ArrayList[6];
        for (int i = 0; i < graph.length; i++) {
            this.graph[i] = new ArrayList<>();
        }
        this.graph[1].add(2);
        this.graph[1].add(3);
        this.graph[2].add(1);
        this.graph[2].add(4);
        this.graph[3].add(1);
        this.graph[3].add(4);
        this.graph[4].add(2);
        this.graph[4].add(5);
        this.map = new HashMap<>();
        this.map.put(1, new Node(1,60.209231, 24.952392));
        this.map.put(2, new Node(2, 60.209325, 24.954199));
        this.map.put(3, new Node(3, 60.208868, 24.952980));
        this.map.put(4, new Node(4, 60.209376, 24.955820));
        this.map.put(5, new Node(5, 60.209017, 24.955841));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void priorityQueueWorks() {
        CoordinateDistance tool = new CoordinateDistance();
        PriorityQueue<AStarNode> priorityQueue = new PriorityQueue<>();
        double goalLat = this.map.get(5).getLat();
        double goalLon = this.map.get(5).getLon();
        AStarNode node = new AStarNode(1, 0, tool.distance(this.map.get(1).getLat(), this.map.get(1).getLon(), goalLat, goalLon));
        priorityQueue.add(node);
        for (int i = 2; i <= 5; i++) {
            AStarNode n = new AStarNode(i, Integer.MAX_VALUE, tool.distance(this.map.get(i).getLat(), this.map.get(i).getLon(), goalLat, goalLon));
            priorityQueue.add(n);
        }
        assertEquals(1, priorityQueue.poll().getId());
        assertEquals(5, priorityQueue.poll().getId());
        assertEquals(4, priorityQueue.poll().getId());
        assertEquals(2, priorityQueue.poll().getId());
        assertEquals(3, priorityQueue.poll().getId());
    }

    @Test
    public void hashCodeWorks(){
        for (int i = 0; i < 10000; i++) {
            AStarNode node = new AStarNode(i * 21, 12000, 120000);
            assertEquals(i * 21, node.hashCode());
        }
    }

    @Test
    public void equalsWorks(){
        for (int i = 0; i < 10000; i++) {
            AStarNode w = new AStarNode(i, 10210, 10000);
            AStarNode p = new AStarNode(i, 10210, 10000);
            AStarNode c = new AStarNode(i + 2, 12022, 10000);
            assertEquals(w, p);
            assertNotEquals(w, c);
            assertEquals(false, p.equals(null));
            assertEquals(false, p.equals("" + i));
        }
    }
}
