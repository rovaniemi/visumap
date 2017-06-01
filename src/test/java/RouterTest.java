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
    public void ifCoordinateIsSameNearestPointIsIt(){
        Router router = new Router();
        Map<Long,Node> nodes = new HashMap<>();
        Node node = new Node(1,65.2222,43.2121);
        nodes.put(1l, node);
        nodes.put(2l,new Node(2,64.2222,48.2121));
        nodes.put(3l,new Node(3,66.2222,44.2121));
        nodes.put(4l,new Node(4,65.2222,47.2121));
        Assert.assertEquals(node, router.findNearestPoint(65.2222,43.2121, nodes));
    }
}
