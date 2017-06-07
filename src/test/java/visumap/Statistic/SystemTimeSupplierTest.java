package visumap.Statistic;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SystemTimeSupplierTest {

    public SystemTimeSupplierTest() {
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
    public void getRightTime() {
        SystemTimeSupplier systemTimeSupplier = new SystemTimeSupplier();
        assertEquals(nanosecondsToMilliseconds(System.nanoTime()),nanosecondsToMilliseconds(systemTimeSupplier.getNanoseconds()));
    }

    @Test
    public void notGetWrongTime() {
        SystemTimeSupplier systemTimeSupplier = new SystemTimeSupplier();
        assertNotEquals(nanosecondsToMilliseconds(System.nanoTime()),nanosecondsToMilliseconds(systemTimeSupplier.getNanoseconds()) + 1);
    }

    public long nanosecondsToMilliseconds(long nanoseconds){
        return nanoseconds / 1000000;
    }
}