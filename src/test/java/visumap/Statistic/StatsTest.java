package visumap.Statistic;

import org.junit.*;
import visumap.Graph.Node;
import visumap.Graph.Weight;

import java.util.concurrent.atomic.AtomicLong;

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
            stats.addNodeE(new Node(12.12222,12.12222, new Weight[12]));
        }
        assertEquals(10000, stats.getEveryNode().size());
    }
}
