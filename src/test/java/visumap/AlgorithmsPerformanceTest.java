package visumap;

import org.junit.*;
import visumap.Graph.Node;
import visumap.Graph.Weight;
import visumap.Graph.WeightComparator;
import visumap.Statistic.Stats;
import visumap.Structures.MinHeap;

import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AlgorithmsPerformanceTest {

    public AlgorithmsPerformanceTest(){

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
        Router router = new Router();
        long astarSum = 0;
        long dijkstraSum = 0;
        for (int i = 0; i < 1000; i++) {
            double minLat = 65.8087;
            double maxLat = 65.8612;
            double minLon = 24.0916;
            double maxLon = 24.2152;
            Node first = new Node(-1, ThreadLocalRandom.current().nextDouble(minLat, maxLat),ThreadLocalRandom.current().nextDouble(minLon, maxLon));
            Node second = new Node( -2, ThreadLocalRandom.current().nextDouble(minLat, maxLat), ThreadLocalRandom.current().nextDouble(minLon, maxLon));
            long start = System.currentTimeMillis();
            Stats dijkstra = router.visualizeAlgorithm("tornio", "dijkstra", first, second);
            long stop = System.currentTimeMillis();
            dijkstraSum += stop - start;
            start = System.currentTimeMillis();
            Stats astar = router.visualizeAlgorithm("tornio", "astar", first, second);
            stop = System.currentTimeMillis();
            astarSum += stop - start;
        }

        System.out.println("astar: " + astarSum / 1000 + " ms");
        System.out.println("dijkstra: " + dijkstraSum/ 1000 + " ms");
    }

    @Test
    public void performanceTest2(){

    }
}
