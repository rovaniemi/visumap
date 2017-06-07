package visumap.Tools;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoordinateDistanceTest {

    List<Double[]> points;
    CoordinateDistance tool;

    public CoordinateDistanceTest(){
        this.tool = new CoordinateDistance();
        this.points = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            points.add(new Double[]{68.64 + (i * 0.00001) ,27.52 - (i * 0.00001)});
        }
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
    public void distanceIsZeroIfPointsIsSame(){
        for (int i = 0; i < 10000; i++) {
            Assert.assertEquals(0, tool.distance(this.points.get(i)[0],this.points.get(i)[1],this.points.get(i)[0],this.points.get(i)[1]));
        }
    }

    @Test
    public void distanceIsRigth(){
        Assert.assertEquals(190003, tool.distance(60.246496,24.943493,60.242496, 24.91002));
        Assert.assertEquals(242148, tool.distance(50.246496,24.943493,50.242496, 24.91002));
    }
}
