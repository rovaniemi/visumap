package Graph;

import org.junit.*;
import pathfinder.Graph.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NodeTest {

    private static final double DELTA = 1e-15;

    public NodeTest(){

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

    //example test for travis
    @Test
    public void equalsIfSame(){
        Node node = new Node(12341234, 12.223, 12.223);
        assertEquals(node.getId(), node.getId());
    }
}
