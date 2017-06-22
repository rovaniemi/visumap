package visumap.Algorithms;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import visumap.Graph.Node;

public class QuickHullTest {

    private QuickHull algorithm;

    @Before
    public void setUp() {
        algorithm = new QuickHull();
    }

    @Test
    public void testAlgorithm() {
        List<Node> points = new ArrayList<>();
        points.add(new Node(1,1, null));
        points.add(new Node(2,6, null));
        points.add(new Node(3,4, null));
        points.add(new Node(9,4, null));
        points.add(new Node(7,5, null));
        points.add(new Node(7,3, null));
        points.add(new Node(6,8, null));
        points.add(new Node(6,8, null));
        points.add(new Node(1,0, null));

        List<Node> convexHull = new ArrayList<>();
        convexHull.add(new Node(1,0, null));
        convexHull.add(new Node(1,1, null));
        convexHull.add(new Node(2,6, null));
        convexHull.add(new Node(6,8, null));
        convexHull.add(new Node(9,4, null));
        convexHull.add(new Node(7,3, null));

        List<Node> finish = algorithm.quickHull(points);

        for (int i = 0; i < finish.size(); i++) {
            Assert.assertEquals(finish.get(i).getLa(), convexHull.get(i).getLa(), 0.0);
            Assert.assertEquals(finish.get(i).getLo(), convexHull.get(i).getLo(), 0.0);
        }
    }

    @Test
    public void testOnlyTwoPoints() {

        List<Node> points = new ArrayList<>();
        points.add(new Node(6,4, null));
        points.add(new Node(6,8, null));

        List<Node> calculatedHull = algorithm.quickHull(points);

        Assert.assertEquals(6.0, calculatedHull.get(0).getLa(), 0.0);
        Assert.assertEquals(4.0, calculatedHull.get(0).getLo(), 0.0);

        Assert.assertEquals(6.0, calculatedHull.get(1).getLa(), 0.0);
        Assert.assertEquals(8.0, calculatedHull.get(1).getLo(), 0.0);
    }
}