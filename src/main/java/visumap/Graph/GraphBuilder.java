package visumap.Graph;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GraphBuilder {

    private Node[] readJson(String fileName){
        List<Node> list = new ArrayList<>(10806049);
        int i = 0;
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            Gson gson = createGson();
            reader.beginArray();
            while (reader.hasNext()){
                if(i++ % 10000 == 0) System.out.println("Reading nodes: " + (i - 1));
                list.add(gson.fromJson(reader,Node.class));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (list.toArray(new Node[list.size()]));
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Node.class, new NodeDeserializer())
                .create();
    }

    private static class NodeDeserializer implements JsonDeserializer<Node> {
        @Override
        public Node deserialize(JsonElement json,
                                Type type,
                                JsonDeserializationContext context) throws JsonParseException {
            JsonObject jobject = json.getAsJsonObject();

            double la = jobject.get("la").getAsFloat();
            double lo = jobject.get("lo").getAsFloat();
            Weight[] e = readEdges(jobject.get("e").getAsJsonArray());
            return new Node(la, lo, e);
        }

        private static Weight[] readEdges(JsonArray array) {
            Weight[] e = new Weight[array.size()];
            for (int j = 0; j < array.size(); j++) {
                e[j] = readWeight(array.get(j).getAsJsonObject());
            }
            return e;
        }

        private static Weight readWeight(JsonObject jsonObject) {
            return new Weight(
                    jsonObject.get("i").getAsInt(),
                    jsonObject.get("w").getAsInt()
            );
        }
    }

    public Node[] createGraph(String fileName){
        return readJson(fileName);
    }
}
