package visumap;

import com.google.gson.Gson;
import org.junit.*;
import visumap.Graph.Node;
import visumap.Statistic.Stats;
import visumap.Statistic.StatsJson;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class RouterTest {

    public RouterTest(){

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

    // "id":"51150","la": "60.227325439453125", "lo" : "25.01201820373535"
    // "id":"243784","la": "60.2126579284668", "lo" : "24.95928955078125"
    // Google maps: 3.7km

    @Test
    public void distanceIsRightWithDijkstra(){
        Router router = new Router();
        Gson gson = new Gson();
        StatsJson answer = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{51150,243784}), StatsJson.class);
        assertTrue(360000 <= answer.getDistance() && answer.getDistance() <= 380000);
    }

    // "id":"51150","la": "60.227325439453125", "lo" : "25.01201820373535"
    // "id":"243784","la": "60.2126579284668", "lo" : "24.95928955078125"
    // Google maps: 3.7km
    @Test
    public void distanceIsRightWithAstar(){
        Router router = new Router();
        Gson gson = new Gson();
        StatsJson answer = gson.fromJson(router.visualizeAlgorithm("astar",new int[]{51150,243784}), StatsJson.class);
        assertTrue(360000 <= answer.getDistance() && answer.getDistance() <= 380000);
    }

    @Test
    public void everyAlgorithmGiveSameAnswer(){
        Router router = new Router();
        Gson gson = new Gson();
        for (int i = 0; i < 100; i++) {
            int[] points = gson.fromJson(router.randomPoints(), int[].class);
            StatsJson dijkstra = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{points[0],points[1]}), StatsJson.class);
            StatsJson astar = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{points[0],points[1]}), StatsJson.class);
            Assert.assertEquals(dijkstra.getDistance(), astar.getDistance());
        }
    }

    @Test
    public void ifSameGoalAndStart(){
        Router router = new Router();
        Gson gson = new Gson();
        for (int i = 0; i < 100; i++) {
            StatsJson dijkstra = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{i,i}), StatsJson.class);
            StatsJson astar = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{i,i}), StatsJson.class);
            Assert.assertEquals(dijkstra.getDistance(), astar.getDistance());
        }
    }

    @Test
    public void sameAnswerIfStartAndGoalNodeIsSame(){
        Router router = new Router();
        Gson gson = new Gson();
        for (int i = 0; i < 100; i++) {
            StatsJson dijkstra = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{i,i}), StatsJson.class);
            StatsJson astar = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{i,i}), StatsJson.class);
            Assert.assertEquals(dijkstra.getDistance(), astar.getDistance());
        }
    }

    @Test
    public void ifCityIsNotInMapReturnInvalidCity(){
        Router router = new Router();
        Gson gson = new Gson();
        StatsJson answer = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{-1,243784}), StatsJson.class);
        Assert.assertEquals("Invalid points", answer.getMessage());
        answer = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{2,-1}), StatsJson.class);
        Assert.assertEquals("Invalid points", answer.getMessage());
        answer = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{200030004,243784}), StatsJson.class);
        Assert.assertEquals("Invalid points", answer.getMessage());
        answer = gson.fromJson(router.visualizeAlgorithm("dijkstra",new int[]{3,243784940}), StatsJson.class);
        Assert.assertEquals("Invalid points", answer.getMessage());
    }

    @Test
    public void ifAlgorithIsNotValidReturnInvalidCity(){
        Router router = new Router();
        Gson gson = new Gson();
        StatsJson answer = gson.fromJson(router.visualizeAlgorithm("dijkstdfra",new int[]{51150,243784}), StatsJson.class);
        Assert.assertEquals("Invalid algorithm", answer.getMessage());
    }
}