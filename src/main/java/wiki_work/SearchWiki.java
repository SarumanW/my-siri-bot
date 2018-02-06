package wiki_work;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class SearchWiki {
    private String queryJSON = "https://en.wikipedia.org/w/api.php?format=json" +
            "&action=query&prop=extracts&exintro=&explaintext=&titles=";
    private URLReader urlReader;
    private Map<String, Object> JSONMap;

    public SearchWiki(){
        urlReader = new URLReader();
    }

    public String run(String word){
        word = word.replaceAll("\\s", "%20");
        queryJSON = new StringBuilder().append(queryJSON).append(word).toString();
        String output = "";
        String result = "";

        try {
            JSONObject jsonObject = urlReader.readJsonFromUrl(queryJSON);
            JSONMap = Mapper.convertJSONtoMap(jsonObject);

            if (JSONMap.containsKey("extract")) {
                result = (String) JSONMap.get("extract");
            }

            if(result.isEmpty()) {
                output = "Your query is not correct. Try one more time";
            } else if(result.contains("refer to") || result.contains("may mean")){
                output = findRefers(result);
            } else {
                output = result;
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }

        JSONMap.clear();
        return output;
    }

    private String findRefers(String result) throws IOException{
        String[] array = result.split("\n");
        String out = "";
        StringBuffer sb = new StringBuffer(out);
        for(int i = 1; i<array.length - 1; i++){
            sb.append(array[i] + "\n");
        }
        return sb.toString();
    }
}
