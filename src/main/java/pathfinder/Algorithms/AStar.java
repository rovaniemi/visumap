package pathfinder.Algorithms;

import pathfinder.Graph.Node;
import pathfinder.Graph.Weight;

import java.util.*;

/**
 * Astar luokka hoitaa astar algoritmin lyhyimmän polun etsinnän, joku bugi vielä, ei toimi.
 */

public class AStar {

    /**
     * Astar hoitaa algoritmin ja palauttaa lyhyimmän polun.
     * @param goal maalisolmun id.
     * @param start lähtösolmun id.
     * @param graph vieruslista.
     * @param nodes nodemap, josta saa latituden ja longituden.
     * @return lyhin reitti senttimetreissä.
     */

    public long astar(Map<Integer, Node> nodes, List<Weight>[] graph, int start, int goal){
        long[] toStart = new long[graph.length];
        long[] toGoal = new long[graph.length];
        int[] path = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            toStart[i] = Long.MAX_VALUE;
            toGoal[i] = 0;//distance(nodes.get(goal).getLat(),nodes.get(goal).getLon(),nodes.get(i).getLat(),nodes.get(i).getLon());
            path[i] = -1;
        }

        PriorityQueue<NodeC> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < graph.length; i++) {
            priorityQueue.add(new NodeC(i,toStart[i],toGoal[i]));
        }

        toStart[start] = 0;
        Set<Integer> set = new HashSet<>();
        while(!set.contains(goal)){
            NodeC node = priorityQueue.poll();
            int nodeid = node.getId();
            set.add(nodeid);
            for (int i = 0; i < graph[nodeid].size(); i++) {
                Weight nextNode = graph[nodeid].get(i);
                if(toStart[nodeid] > toStart[nextNode.getId()] + nextNode.getWeight()){
                    toStart[nodeid] = toStart[nextNode.getId()] + nextNode.getWeight();
                    priorityQueue.remove(new Node(nodeid,1,1));
                    priorityQueue.add(new NodeC(nodeid,toStart[nodeid],toGoal[nodeid]));
                    path[nodeid] = graph[nodeid].get(i).getId();

                }
            }

        }
        return shortestPath(nodes, path, start, goal);
    }

    /**
     * ShortestPath hoitaa path taulukosta lopullisen pituuden laskemisen.
     * @param goal maalisolmun id.
     * @param start lähtösolmun id.
     * @param nodes nodemap, josta saa latituden ja longituden.
     * @return lyhin reitti senttimetreissä.
     */

    private long shortestPath(Map<Integer, Node> nodes, int[] path, int start, int goal){
        long distance = 0;
        int next = goal;
        while(true){
            if(next == -1){
                break;
            }
            distance += distance(nodes.get(next).getLat(),nodes.get(next).getLon(),nodes.get(path[next]).getLat(),nodes.get(path[next]).getLon());
            if(next == start) {
                return distance;
            }
            next = path[next];
        }
        return distance;
    }

    /**
     * Distance -funktio hoitaa etäisyyden laskemisen kahden pisteen välillä.
     * @param lat1 ensimmäisen pisteen lat.
     * @param lon1 ensimmäisen pisteen lon.
     * @param lat2 toisen pisteen lat.
     * @param lon2 toisen pisteen lon.
     * @return lyhin reitti senttimetreissä.
     */

    private long distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist *= 60 * 1.1515 * 1.609344 * 100000;
        return (int) (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    /**
     * NoceC -luokka hoitaa keossa olevien olioiden luomisen ja niiden vertailun.
     */

    private class NodeC implements Comparable<NodeC>{

        private Integer id;
        private long toGoal;
        private long toStart;

        public NodeC(int id, long toStart, long toGoal) {
            this.id = id;
            this.toStart = toStart;
            this.toGoal = toGoal;
        }

        @Override
        public int compareTo(NodeC o) {
            return (int)((toStart + toGoal) - (o.toStart - o.toGoal));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NodeC nodeC = (NodeC) o;

            if (id != nodeC.id) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return this.id;
        }

        public Integer getId() {
            return id;
        }
    }
}
