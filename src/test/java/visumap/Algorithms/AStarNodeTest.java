package visumap.Algorithms;

import org.junit.*;
import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Structures.MinHeap;
import visumap.Tools.CoordinateDistance;

import java.util.*;

import static org.junit.Assert.assertEquals;

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
        this.map.put(1, new Node(60.209231, 24.952392, new Weight[12]));
        this.map.put(2, new Node(60.209325, 24.954199, new Weight[12]));
        this.map.put(3, new Node(60.208868, 24.952980, new Weight[12]));
        this.map.put(4, new Node(60.209376, 24.955820, new Weight[12]));
        this.map.put(5, new Node(60.209017, 24.955841, new Weight[12]));
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
    public void minHeapWorks() {
        CoordinateDistance tool = new CoordinateDistance();
        MinHeap<AStarNode> minHeap = new MinHeap<>(new AStarComparator());
        double goalLat = this.map.get(5).getLa();
        double goalLon = this.map.get(5).getLo();
        AStarNode node = new AStarNode(1, 0, tool.distance(this.map.get(1).getLa(), this.map.get(1).getLo(), goalLat, goalLon));
        minHeap.add(node);
        for (int i = 2; i <= 5; i++) {
            AStarNode n = new AStarNode(i, Integer.MAX_VALUE, tool.distance(this.map.get(i).getLa(), this.map.get(i).getLo(), goalLat, goalLon));
            minHeap.add(n);
        }
        assertEquals(1, minHeap.poll().getId());
        assertEquals(5, minHeap.poll().getId());
        assertEquals(4, minHeap.poll().getId());
        assertEquals(2, minHeap.poll().getId());
        assertEquals(3, minHeap.poll().getId());
    }
}
