package visumap.Algorithms;

import org.junit.Test;
import visumap.Graph.Weight;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DijkstraTest {


    public void smallTest(int n, int[] where, int[] to, long[] distance, long realResult) {
        Dijkstra dijkstra = new Dijkstra();
        long algorithmResult = dijkstra.getShortestPath(null, greateList(n, where, to, distance), 1, n);
        assertTrue(realResult == algorithmResult);
    }

    public void bigTest(int n, int[] where, int[] to, long[] distance, long realResult) {
        Dijkstra dijkstra = new Dijkstra();
        long algorithmResult = dijkstra.getShortestPath(null, greateList(n, where, to, distance),1, n);
        assertTrue(realResult == algorithmResult);
    }

    @Test
    public void small() {
        smallTest(3, new int[] {1}, new int[] {2}, new long[] {5}, -1);
        smallTest(3, new int[] {1}, new int[] {3}, new long[] {5}, 5);
        smallTest(3, new int[] {2, 2}, new int[] {1, 3}, new long[] {1, 1}, 2);
        smallTest(2, new int[] {}, new int[] {}, new long[] {}, -1);
        smallTest(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 4, 3}, 7);
        smallTest(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 3, 4}, 6);
        smallTest(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 1, 4}, 4);
        smallTest(3, new int[] {1, 2}, new int[] {2, 3}, new long[] {5, 3}, 8);
        smallTest(3, new int[] {1, 1}, new int[] {2, 3}, new long[] {2, 3}, 3);
        smallTest(3, new int[] {1, 2, 1}, new int[] {3, 3, 2}, new long[] {9, 1, 1}, 2);
        smallTest(3, new int[] {1, 2, 1}, new int[] {3, 3, 2}, new long[] {1, 9, 9}, 1);
        smallTest(2, new int[] {1}, new int[] {2}, new long[] {5}, 5);
        smallTest(5, new int[] {}, new int[] {}, new long[] {}, -1);
        smallTest(5, new int[] {1}, new int[] {5}, new long[] {10}, 10);
        smallTest(3, new int[] {}, new int[] {}, new long[] {}, -1);
        smallTest(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 2, 2, 2, 2}, 8);
        smallTest(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 3, 3, 3, 3}, 10);
        smallTest(2, new int[] {2}, new int[] {1}, new long[] {5}, 5);
        smallTest(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new long[] {999999999, 999999999, 999999999}, (long)3*999999999);
    }

    @Test
    public void big1() {
        int n = 1000000;
        int[] where = new int[n - 1];
        int[] to = new int[n - 1];
        long[] distance = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            where[i] = i + 1;
            to[i] = i + 2;
            distance[i] = 1;
        }
        bigTest(n, where, to, distance, n - 1);
    }

    @Test
    public void big2() {
        int n = 1000000;
        int[] where = new int[n - 1];
        int[] to = new int[n - 1];
        long[] distance = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            where[i] = i + 1;
            to[i] = i + 2;
            distance[i] = 999999999;
        }
        bigTest(n, where, to, distance, (long) 999999999 * (n - 1));
    }

    @Test
    public void big3() {
        int n = 1000000;
        int[] where = new int[(n - 1) / 3 * 4];
        int[] to = new int[(n - 1) / 3 * 4];
        long[] distance = new long[(n - 1) / 3 * 4];
        int c = 0;
        for (int i = 0; i < (n - 1) / 3 * 4; i += 4) {
            where[i] = i + 1 - c;
            to[i] = i + 2 - c;
            where[i + 1] = i + 1 - c;
            to[i + 1] = i + 3 - c;
            where[i + 2] = i + 2 - c;
            to[i + 2] = i + 4 - c;
            where[i + 3] = i + 3 - c;
            to[i + 3] = i + 4 - c;
            if (c % 2 == 0) {
                distance[i] = 1;
                distance[i + 1] = 2;
                distance[i + 2] = 1;
                distance[i + 3] = 2;
            } else {
                distance[i] = 2;
                distance[i + 1] = 1;
                distance[i + 2] = 2;
                distance[i + 3] = 1;
            }
            c++;
        }
        bigTest(n, where, to, distance, (n - 1) / 3 * 2);
    }

    public List<Weight>[] greateList(int length, int[] where, int[] to, long[] distance){
        List<Weight>[] vl = new ArrayList[length+1];
        for(int i=1; i<=length; i++)
            vl[i]=new ArrayList();

        for(int i=0; i<where.length; i++){
            vl[where[i]].add(new Weight(to[i], distance[i]));
            vl[to[i]].add(new Weight(where[i], distance[i]));
        }
        return vl;
    }
}

