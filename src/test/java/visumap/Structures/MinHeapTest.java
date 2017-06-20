package visumap.Structures;

import org.junit.*;
import visumap.Algorithms.AStarNode;
import visumap.Algorithms.AStarComparator;
import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Algorithms.DijkstraComparator;
import visumap.Tools.CoordinateDistance;

import java.util.*;

import static org.junit.Assert.*;

public class MinHeapTest {

    private MinHeap<AStarNode> heap;
    private List<Integer>[] graph;
    private Map<Integer, Node> map;

    public MinHeapTest() {
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
        this.map.put(1, new Node(60.209231, 24.952392, new Weight[20000]));
        this.map.put(2, new Node(60.209325, 24.954199, new Weight[20000]));
        this.map.put(3, new Node(60.208868, 24.952980, new Weight[20000]));
        this.map.put(4, new Node(60.209376, 24.955820, new Weight[20000]));
        this.map.put(5, new Node(60.209017, 24.955841, new Weight[20000]));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.heap = new MinHeap(new AStarComparator());
    }

    @After
    public void tearDown() {
    }


    @Test
    public void testRemovingFromEmptyHeap() {
        assertNull(heap.poll());
    }

    @Test
    public void testAddingMultipleItems() {
        CoordinateDistance tool = new CoordinateDistance();
        double goalLat = this.map.get(5).getLa();
        double goalLon = this.map.get(5).getLo();
        AStarNode node = new AStarNode(1, 0, tool.distance(this.map.get(1).getLa(), this.map.get(1).getLo(), goalLat, goalLon));
        heap.add(node);
        for (int i = 2; i <= 5; i++) {
            AStarNode n = new AStarNode(i, Integer.MAX_VALUE, tool.distance(this.map.get(i).getLa(), this.map.get(i).getLo(), goalLat, goalLon));
            heap.add(n);
        }
        assertEquals(1, heap.poll().getId());
        assertEquals(5, heap.poll().getId());
        assertEquals(4, heap.poll().getId());
        assertEquals(2, heap.poll().getId());
        assertEquals(3, heap.poll().getId());
    }

    @Test
    public void testAddingMillionItems(){
        MinHeap<AStarNode> heap1 = new MinHeap(new AStarComparator());
        for (int i = 1; i <= 1000000; i++) {
            AStarNode node = new AStarNode(i,i,Integer.MAX_VALUE - i * 2);
            heap1.add(node);
        }

        for (int i = 0; i < 1000000; i++) {
            assertEquals(1000000 - i, heap1.poll().getId());
        }
        assertNull(heap1.poll());
    }

    @Test
    public void isEmptyWork(){
        MinHeap<Weight> heap = new MinHeap<>(new DijkstraComparator());
        for (int i = 0; i < 100; i++) {
            heap.add(new Weight(i,i));
        }
        for (int i = 0; i < 100; i++) {
            assertFalse(heap.isEmpty());
            heap.poll();
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    public void minHeapIsNotTwoTimesSlowerThanPriorityQueue(){
        MinHeap<AStarNode> minHeap = new MinHeap(new AStarComparator());
        PriorityQueue<AStarNode> priorityQueue = new PriorityQueue<>(new AStarComparator());
        int repeat = 1000;
        long minHeapSum = 0;
        long priorityQueueSum = 0;
        for (int j = 0; j < repeat; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                AStarNode node = new AStarNode(i,i,Integer.MAX_VALUE - i * 2);
                minHeap.add(node);
            }
            for (int i = 0; i < 10000; i++) {
                minHeap.poll();
            }
            long end = System.currentTimeMillis();
            minHeapSum += end - start;
            start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                AStarNode node = new AStarNode(i,i,Integer.MAX_VALUE - i * 2);
                priorityQueue.add(node);
            }
            for (int i = 0; i < 10000; i++) {
                priorityQueue.poll();
            }
            end = System.currentTimeMillis();
            priorityQueueSum += end - start;
        }
        assertTrue((double) minHeapSum / priorityQueueSum < 2);
    }

    @Test
    public void differentSizedHeap(){
        for (int i = 0; i < 1000; i++) {
            PriorityQueue<Weight> priorityQueue = new PriorityQueue<>(new DijkstraComparator());
            MinHeap<Weight> weightMinHeap = new MinHeap<>(new DijkstraComparator());
            for (int j = 0; j < i; j++) {
                priorityQueue.add(new Weight(j,(i * 10000) + 1));
                weightMinHeap.add(new Weight(j,(i * 10000) + 1));
            }
            for (int j = 0; j < i; j++) {
                int p = weightMinHeap.poll().getI();
                assertEquals(j,p);
                assertEquals(priorityQueue.poll().getI(), p);
            }
        }
        assertTrue(true);
        for (int i = 0; i < 100; i++) {
            MinHeap<Weight> weightMinHeap = new MinHeap<>(new DijkstraComparator());
            PriorityQueue<Weight> priorityQueue = new PriorityQueue<>(new DijkstraComparator());
            for (int j = 0; j < i * 2; j++) {
                priorityQueue.add(new Weight(j,(i * 10000) + 1));
                weightMinHeap.add(new Weight(j,(i * 10000) + 1));
            }
            for (int j = 0; j < i; j++) {
                int p = weightMinHeap.poll().getI();
                assertEquals(j,p);
                assertEquals(priorityQueue.poll().getI(), p);
            }
            for (int j = 0; j < i; j++) {
                priorityQueue.add(new Weight(j,(i * 10000) + 1));
                weightMinHeap.add(new Weight(j,(i * 10000) + 1));
            }
            for (int j = 0; j < i * 2; j++) {
                int p = weightMinHeap.poll().getI();
                assertEquals(j,p);
                assertEquals(priorityQueue.poll().getI(), p);
            }
        }
    }
}
