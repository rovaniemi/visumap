package visumap;

import org.junit.*;
import visumap.Graph.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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
        Assert.assertEquals("187043", answer);
    }

    @Test
    public void distanceIsRightWithAstar(){
        Router router = new Router();
        Node first = new Node(-1,65.858057,24.139239);
        Node second = new Node( -2, 65.844018, 24.149615);
        String answer = router.visualizeAlgorithm("tornio", "astar", first, second);
        Assert.assertEquals("187043", answer);
    }

    @Test
    public void everyAlgorithmGiveSameAnswer(){
        Router router = new Router();
        for (int i = 0; i < 10; i++) {
            double minLat = 65.8087;
            double maxLat = 65.8612;
            double minLon = 24.0916;
            double maxLon = 24.2152;
            Node first = new Node(-1,ThreadLocalRandom.current().nextDouble(minLat, maxLat),ThreadLocalRandom.current().nextDouble(minLon, maxLon));
            Node second = new Node( -2, ThreadLocalRandom.current().nextDouble(minLat, maxLat), ThreadLocalRandom.current().nextDouble(minLon, maxLon));
            String dijkstra = router.visualizeAlgorithm("tornio", "dijkstra", first, second);
            String astar = router.visualizeAlgorithm("tornio", "astar", first, second);
            Assert.assertEquals(dijkstra, astar);
        }
    }

    @Test
    public void sameAnswerIfStartAndGoalNodeIsSame(){
        Router router = new Router();
        for (int i = 0; i < 100; i++) {
            double minLat = 65.8087;
            double maxLat = 65.8612;
            double minLon = 24.0916;
            double maxLon = 24.2152;
            Node first = new Node(-1,ThreadLocalRandom.current().nextDouble(minLat, maxLat),ThreadLocalRandom.current().nextDouble(minLon, maxLon));
            String dijkstra = router.visualizeAlgorithm("tornio", "getShortestPath", first, first);
            String astar = router.visualizeAlgorithm("tornio", "getShortestPath", first, first);
            Assert.assertEquals(dijkstra, astar);
        }
    }

    @Test
    public void ifCityIsNotInMapReturnInvalidCity(){
        Router router = new Router();
        Node first = new Node(-1,65.858057,24.139239);
        Node second = new Node( -2, 65.844018, 24.149615);
        String answer = router.visualizeAlgorithm("aosdfij", "getShortestPath", first, second);
        Assert.assertEquals("Invalid city", answer);
    }

    @Test
    public void ifAlgorithIsNotValidReturnInvalidCity(){
        Router router = new Router();
        Node first = new Node(-1,65.858057,24.139239);
        Node second = new Node( -2, 65.844018, 24.149615);
        String answer = router.visualizeAlgorithm("tornio", "asdfasdf", first, second);
        Assert.assertEquals("Invalid algorithm", answer);
    }
}
