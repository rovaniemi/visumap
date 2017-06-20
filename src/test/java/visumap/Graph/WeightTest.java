package visumap.Graph;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
    public void getterAndSetterWorks(){
        List<Weight> weights = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            weights.add(new Weight(i, i * 1000));
        }
        for (int i = 0; i < 10000; i++) {
            assertTrue(weights.get(i).getI() == i);
            assertTrue(weights.get(i).getW() == i * 1000);
        }
    }
}
