package visumap.Graph;

import org.junit.*;

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
    public void equalsWorks(){
        for (int i = 0; i < 10000; i++) {
            Weight w = new Weight(i, 10210);
            Weight p = new Weight(i, 10210);
            Weight c = new Weight(i + 2, 12022);
            assertEquals(w, p);
            assertNotEquals(w, c);
            assertEquals(false, p.equals(null));
            assertEquals(false, p.equals("" + i));
        }
    }
}
