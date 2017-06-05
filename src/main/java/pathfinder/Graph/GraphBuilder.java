package pathfinder.Graph;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.*;

public class GraphBuilder {



    public GraphBuilder(){

    }

    private List<Node> readData(String fileName){
        List<Node> list = new ArrayList<>();
        try(JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            Gson gson = new GsonBuilder().create();
            jsonReader.beginArray();
            while (jsonReader.hasNext()){
                Node node = gson.fromJson(jsonReader,Node.class);
                list.add(node);
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * greateGraph metodi rakentaa vieruslistan.
     * @param fileName json -tiedoston nimi.
     * @return metodi palauttaa vieruslista esityksen verkosta.
     */

    public List<Weight>[] createGraph(String fileName){
        List<Node> nodes = readData(fileName);
        List<Weight>[] weights = new ArrayList[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            weights[i] = new ArrayList<>();
            if(nodes.get(i).getId() == i){
                Iterator j = nodes.get(i).getEdges().iterator();
                while (j.hasNext()){
                    weights[i].add((Weight) j.next());
                }
            }
        }
        return weights;
    }

    public Map<Integer, Node> createNodeMap(String fileName) {
        Map<Integer, Node> nodes = new HashMap<>();
        List<Node> nodeList = readData(fileName);
        for (int i = 0; i < nodeList.size(); i++) {
            nodes.put(nodeList.get(i).getId(), nodeList.get(i));
        }
        return nodes;
    }
}
