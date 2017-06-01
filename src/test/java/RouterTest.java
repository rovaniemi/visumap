import org.junit.*;
import pathfinder.Graph.Node;
import pathfinder.Router;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RouterTest {

    public RouterTest(){

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
    public void ifCoordinateIsSameNearestPointIsSame(){
        Router router = new Router();
        Map<Integer, Node> nodes = new HashMap<>();
        Node node = new Node(1,65.2222,43.2121);
        nodes.put(1, node);
        nodes.put(2, new Node(2,64.2222,48.2121));
        nodes.put(3, new Node(3,66.2222,44.2121));
        nodes.put(4, new Node(4,65.2222,47.2121));
        Assert.assertEquals(node, router.findNearestPoint(node, nodes));
    }

    @Test
    public void distanceIsRightWithDijkstra(){
        Router router = new Router();
        Node first = new Node(-1,65.858057,24.139239);
        Node second = new Node( -2, 65.844018, 24.149615);
        String answer = router.visualizeAlgorithm("tornio", "dijkstra", first, second);
        Assert.assertEquals("186992", answer);
    }
}
