package pathfinder.Graph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    /**
     * Graph luokassa säilytetään tieto yhdestä verkosta minkä ohjelma omistaa.
     */

    private List<Weight>[] graph;

    public Graph(){
        GraphBuilder graphBuilder = new GraphBuilder();
        this.graph = graphBuilder.greateGraph("maps/tornio-pretty.json");
    }

    public List<Weight>[] getGraph() {
        return graph;
    }

    public void setGraph(List<Weight>[] graph) {
        this.graph = graph;
    }
}
