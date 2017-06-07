package visumap.Graph;

import org.junit.*;

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

    @Test
    public void hashCodeIsId(){
        for (int i = 0; i < 10000; i++) {
            Node n = new Node(i, 12.222, 12.222);
            assertEquals(i, n.hashCode());
        }
    }

    @Test
    public void equalsWorksFine(){
        for (int i = 0; i < 10000; i++) {
            Node n = new Node(i, 12.222, 12.222);
            Node p = new Node(i, 12.222, 12.212);
            Node s = new Node(i + 1, 12.222, 12.222);
            assertEquals(n,p);
            assertEquals(false, n.equals(null));
            assertEquals(false, n.equals("" + i));
            assertEquals(false, n.equals(s));
        }
    }

}
