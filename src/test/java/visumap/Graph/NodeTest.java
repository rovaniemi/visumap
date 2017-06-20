package visumap.Graph;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

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
    public void getterAndSetterWorks(){
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            nodes.add(new Node(12.22, 12.33, new Weight[12]));
        }
        for (int i = 0; i < 10000; i++) {
            assertTrue(nodes.get(i).getLa() == 12.22);
            assertTrue(nodes.get(i).getLo() == 12.33);
            assertTrue(nodes.get(i).getE().length == 12);
        }
    }

}
