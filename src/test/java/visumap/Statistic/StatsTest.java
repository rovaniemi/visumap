package visumap.Statistic;

import org.junit.*;
import visumap.Graph.Node;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StatsTest {


    private static final long SECONDS_TO_NANOSECONDS = 1000000000;
    private static final long MINUTES_TO_NANOSECONDS = 60 * SECONDS_TO_NANOSECONDS;
    private static final long HOURS_TO_NANOSECONDS = 60 * MINUTES_TO_NANOSECONDS;
    private static final long DAY_TO_NANOSECONDS = 24 * HOURS_TO_NANOSECONDS;

    private AtomicLong time;
    private TimeSupplier timeSupplier;


    public StatsTest(){

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.time = new AtomicLong(System.nanoTime());
        this.timeSupplier = time::get;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addNodeWorks(){
        Stats stats = new Stats();
        for (int i = 0; i < 10000; i++) {
            stats.addNodeE(new Node(i,12.12222,12.12222));
        }
        assertEquals(10000, stats.getEveryNode().size());
    }

    @Test
    public void shortestPathListAndEveryNodeListNotHaveSameNodes(){
        Stats stats = new Stats();
        for (int i = 0; i < 10000; i++) {
            stats.addNodeE(new Node(i,12.12222,12.12222));
        }
        for (int i = 0; i < 100; i++) {
            stats.addNodeS(new Node(i,12.12222,12.12222));
        }
        assertEquals(9900, stats.getEveryNode().size());
        assertEquals(100, stats.getShortestPath().size());
    }

    @Test
    public void newTrackerMillisecondsZero() {
        Stats stats = new Stats();
        assertEquals(stats.stopTimeTracking(),0);
    }

    @Test
    public void millisecondsCorrectAfterStartAndSixMinutesTwentySeconds() {
        Stats stats = new Stats(this.timeSupplier);
        stats.startTimeTracking();
        long sixMinutesTwentySeconds = 6 * MINUTES_TO_NANOSECONDS + 20 * SECONDS_TO_NANOSECONDS;
        time.addAndGet(sixMinutesTwentySeconds);
        long change = nanosecondsToMilliseconds(sixMinutesTwentySeconds);
        assertEquals(change, stats.stopTimeTracking());
    }


    @Test
    public void millisecondsCorrectIfStop() {
        Stats stats = new Stats(this.timeSupplier);
        stats.startTimeTracking();
        this.time.addAndGet(6 * HOURS_TO_NANOSECONDS + 12 * SECONDS_TO_NANOSECONDS);
        long change = nanosecondsToMilliseconds(6 * HOURS_TO_NANOSECONDS + 12 * SECONDS_TO_NANOSECONDS);
        assertEquals(change, stats.stopTimeTracking());
    }

    public long nanosecondsToMilliseconds(long nanoseconds){
        return nanoseconds / 1000000;
    }
}
