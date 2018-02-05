
import org.json.JSONObject;
import java.io.*;



public class WikiGetter {

    public static void main(String[] args) {

        URLReader urlReader = new URLReader();
        String jsonPage = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=fish";

        try {
            JSONObject jsonObject = urlReader.readJsonFromUrl(jsonPage);
            System.out.println(jsonObject.get("batchcomplete"));
        } catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
