package Graph;

import org.junit.*;
import pathfinder.Graph.Node;
import pathfinder.Graph.Weight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WeightTest {

    public WeightTest(){

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
        Weight weight = new Weight(123, 12020);
        Weight weight1 = new Weight(123,120220);
        assertEquals(weight,weight1);
        Weight weight2 = new Weight(1233,12222);
        assertNotEquals(weight,weight2);
    }

    @Test
    public void equalsWorksFine(){
        for (int i = 0; i < 10000; i++) {
            Weight n = new Weight(i, 12222);
            Weight p = new Weight(i, 12222);
            Weight s = new Weight(i + 1, 12222);
            assertEquals(true, n.equals(p));
            assertEquals(false, n.equals(null));
            assertEquals(false, n.equals("" + i));
            assertEquals(false, n.equals(s));
        }
    }

    @Test
    public void equalsWorksFinea(){
        Weight n = new Weight(0, 12222);
        Weight p = new Weight(0, 12222);
        n.equals(p);
    }
}
