package visumap.Algorithms;

import visumap.Graph.Node;

import java.util.ArrayList;
import java.util.List;


/**
 * Toteutin convexHull ongelman ratkaisemisen jarvins march algoritmillä jota muokkasin vähän.
 * Tarkka tieto algoritmistä löytyy Cormenin toisen painoksen sivulta 955.
 * Luokka QuickHull saa itselleen Listan nodeja joista kauimmaisten ympärille pitäisi piirtää ympärä.
 */

public class QuickHull {


    public List<Node> quickHull(List<Node> points) {
        List<Node> convexHull = new ArrayList<>();

        //jos pointteja on vähemmän kuin kolme palautetaan ne suoraan.
        if (points.size() < 3) return points;

        // etsitään piste jolla on suurin ja pienin X arvo.
        int minPoint = -1, maxPoint = -1;
        Double minX = Double.MAX_VALUE;
        Double maxX = Double.MIN_VALUE;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getLa() < minX) {
                minX = points.get(i).getLa();
                minPoint = i;
            }
            if (points.get(i).getLa() > maxX) {
                maxX = points.get(i).getLa();
                maxPoint = i;
            }
        }

        // jos tälläistä pointtia ei löytynyt palautetaan, kyseessä on virhetilanne.
        if(maxPoint < 0 || minPoint < 0){
            return convexHull;
        }

        // valitaan kyseiset pisteet ja lisätään ne ConvexHull listaan, jonka jälkeen lähdetään liikkumaan molempiin suuntiin.
        Node A = points.get(minPoint);
        Node B = points.get(maxPoint);
        convexHull.add(A);
        convexHull.add(B);
        points.remove(A);
        points.remove(B);

        List<Node> leftSet = new ArrayList<>();
        List<Node> rightSet = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            Node n = points.get(i);
            if (pointLocation(A, B, n) == -1)
                leftSet.add(n);
            else if (pointLocation(A, B, n) == 1)
                rightSet.add(n);
        }
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);

        return convexHull;
    }

    // etäisyyden laskemiseen käytettävä funktio.

    public double distance(Node A, Node B, Node C) {
        double ABx = B.getLa() - A.getLa();
        double ABy = B.getLo() - A.getLo();
        double num = ABx * (A.getLo() - C.getLo()) - ABy * (A.getLa() - C.getLa());
        if (num < 0) num = -num;
        return num;
    }



    public void hullSet(Node A, Node B, List<Node> set, List<Node> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0) return;
        if (set.size() == 1) {
            Node n = set.get(0);
            set.remove(n);
            hull.add(insertPosition, n);
            return;
        }
        double dist = Double.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Node n = set.get(i);
            double distance = distance(A, B, n);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }
        Node N = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, N);

        ArrayList<Node> leftSetAP = new ArrayList<Node>();
        for (int i = 0; i < set.size(); i++) {
            Node M = set.get(i);
            if (pointLocation(A, N, M) == 1) {
                leftSetAP.add(M);
            }
        }

        ArrayList<Node> leftSetPB = new ArrayList<Node>();
        for (int i = 0; i < set.size(); i++) {
            Node M = set.get(i);
            if (pointLocation(N, B, M) == 1) {
                leftSetPB.add(M);
            }
        }
        hullSet(A, N, leftSetAP, hull);
        hullSet(N, B, leftSetPB, hull);

    }

    public int pointLocation(Node A, Node B, Node P) {
        double cp1 = (B.getLa() - A.getLa()) * (P.getLo() - A.getLo()) - (B.getLo() - A.getLo()) * (P.getLa() - A.getLa());
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }
}