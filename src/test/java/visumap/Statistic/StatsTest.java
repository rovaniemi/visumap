package visumap.Statistic;

import org.junit.*;
import visumap.Graph.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StatsTest {

    public StatsTest(){

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
    public void addNodeWorks(){
        Stats stats = new Stats();
        for (int i = 0; i < 10000; i++) {
            stats.addNodeE(new Node(i,12.12222,12.12222));
        }
        assertEquals(10000, stats.getEveryNode().size());
        assertEquals(10000, stats.getNodeCounter());
    }

    @Test
    public void shortestPathListAndEveryNodeListNotHaveSameNodes(){
        Stats stats = new Stats();
        for (int i = 0; i < 10000; i++) {
            stats.addNodeE(new Node(i,12.12222,12.12222));
        }
        for (int i = 0; i < 100; i++) {
            stats.addNodeS(new Node(i,12.12222,12.12222));
        }
        assertEquals(9900, stats.getEveryNode().size());
        assertEquals(100, stats.getShortestPath().size());
        assertEquals(10000, stats.getNodeCounter());
    }

    @Test
    public void addAndGetRunningTimeWorks(){
        Stats stats = new Stats();
        for (int i = 0; i < 100000; i++) {
            stats.addRunningTime(i);
            assertEquals(i, stats.getRunningTime());
        }
    }
}
