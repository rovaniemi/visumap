package visumap;

import org.junit.*;
import visumap.Graph.Weight;
import visumap.Graph.WeightComparator;
import visumap.Structures.MinHeap;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MinHeapPerformanceTest {

    public MinHeapPerformanceTest(){

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
    public void performanceTest1(){
        WeightComparator wComparator = new WeightComparator();
        PriorityQueue<Weight> priorityQueue = new PriorityQueue<>(wComparator);
        MinHeap<Weight> weightMinHeap = new MinHeap<>(wComparator);

        long start = System.currentTimeMillis();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10000; i++) {
                priorityQueue.add(new Weight(i, ((((i * 1492) % 5) + 1) * (((i * 12939) % 7) + 3)) * 32));
            }
            priorityQueue.clear();
            System.out.println(j);
        }

        long stop = System.currentTimeMillis();

        System.out.println((stop - start) / 10);

        start = System.currentTimeMillis();
        for (int j = 0; j < 10; j++) {
            weightMinHeap = new MinHeap<>(wComparator);
            for (int i = 0; i < 10000; i++) {
                weightMinHeap.add(new Weight(i, ((((i * 1492) % 5) + 1) * (((i * 12939) % 7) + 3)) * 32));
            }
        }
        stop = System.currentTimeMillis();

        System.out.println((stop - start) / 10);
    }

    @Test
    public void performanceTest2(){
        WeightComparator wComparator = new WeightComparator();
        PriorityQueue<Weight> priorityQueue = new PriorityQueue<>(wComparator);
        MinHeap<Weight> weightMinHeap = new MinHeap<>(wComparator);

        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 10000; i++) {
                priorityQueue.add(new Weight(i, ((((i * 1492) % 5) + 1) * (((i * 12939) % 7) + 3)) * 32));
            }

            for (int i = 0; i < 10000; i++) {
                priorityQueue.poll();
            }
        }

        long stop = System.currentTimeMillis();

        System.out.println((stop - start) / 100);

        start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            weightMinHeap = new MinHeap<>(wComparator);
            for (int i = 0; i < 10000; i++) {
                weightMinHeap.add(new Weight(i, ((((i * 1492) % 5) + 1) * (((i * 12939) % 7) + 3)) * 32));
            }

            for (int i = 0; i < 10000; i++) {
                weightMinHeap.poll();
            }
        }
        stop = System.currentTimeMillis();

        System.out.println((stop - start) / 100);
    }
}
