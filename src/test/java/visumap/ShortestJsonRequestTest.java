package visumap;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ShortestJsonRequestTest {

    public ShortestJsonRequestTest(){

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
    public void testingJsonRequest(){
        for (int i = 0; i < 100000; i++) {
            ShortestJsonRequest request = new ShortestJsonRequest();
            request.algorithm = "jotain";
            request.points = new int[2];
            request.points[0] = 123 * i;
            request.points[1] = 321 * i;
            assertEquals("jotain", request.algorithm);
            assertEquals(123 * i, request.points[0]);
            assertEquals(321 * i, request.points[1]);
        }

    }

}
