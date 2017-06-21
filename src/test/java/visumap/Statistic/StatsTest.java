package visumap.Statistic;

import org.junit.*;
import visumap.Graph.Node;
import visumap.Graph.Weight;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void ifMinusDistanceReturnMinusOne(){
        Stats stats = new Stats();
        Node[] nodes = new Node[6];
        nodes[0] = new Node(12,12, new Weight[12]);
        nodes[1] = new Node(12,12, new Weight[12]);

        stats.shortestPath(nodes,new int[]{-1,5,1,2,3,-1},0,1);
        stats.createStats();
        assertEquals(-1, stats.getDistance());
    }
}
