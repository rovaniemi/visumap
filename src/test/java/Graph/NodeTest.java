package Graph;

import org.junit.*;
import pathfinder.Graph.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NodeTest {

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

    @Test
    public void equalsIfSame(){
        Node node = new Node(1233,12.22,12.122);
        Node node2 = new Node(1233,12.22,12.122);
        assertEquals(node,node2);
    }

}
