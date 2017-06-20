package visumap.Graph;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GraphBuilder {

    private Node2[] readJson(String fileName){
        List<Node2> list = new ArrayList<>(10806049);
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            Gson gson = createGson();
            reader.beginArray();
            int i = 0;
            while (reader.hasNext()){
                list.add(gson.fromJson(reader,Node.class));
                if(i++ % 1000 == 0){
                    System.out.println(i - 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (list.toArray(new Node2[list.size()]));
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Node.class, new NodeDeserializer())
                .create();
    }

    private static class NodeDeserializer implements JsonDeserializer<Node2> {
        @Override
        public Node2 deserialize(JsonElement json,
                                Type type,
                                JsonDeserializationContext context) throws JsonParseException {
            JsonObject jobject = json.getAsJsonObject();

            double la = jobject.get("la").getAsFloat();
            double lo = jobject.get("lo").getAsFloat();
            Weight2[] e = readEdges(jobject.get("e").getAsJsonArray());
            return new Node2(la, lo, e);
        }

        private static Weight2[] readEdges(JsonArray array) {
            Weight2[] e = new Weight2[array.size()];
            for (int j = 0; j < array.size(); j++) {
                e[j] = readWeight(array.get(j).getAsJsonObject());
            }
            return e;
        }

        private static Weight2 readWeight(JsonObject jsonObject) {
            return new Weight2(
                    jsonObject.get("i").getAsInt(),
                    jsonObject.get("w").getAsInt()
            );
        }
    }

    public Node2[] createGraph(String fileName){
        return readJson(fileName);
    }
}
