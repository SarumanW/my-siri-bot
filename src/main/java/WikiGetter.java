
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.Map;


public class WikiGetter {

    public static void main(String[] args) {

        URLReader urlReader = new URLReader();
        String jsonPage = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=fish";

        try {
            JSONObject jsonObject = urlReader.readJsonFromUrl(jsonPage);
            Map<String, Object> map = Mapper.convertJSONtoMap(jsonObject);
            map.forEach((k,v) ->
                    System.out.println(k + " = " + v));
        } catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
