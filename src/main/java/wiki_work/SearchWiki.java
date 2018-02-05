package wiki_work;

import org.json.JSONObject;
import wiki_work.Mapper;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SearchWiki {
    private String queryJSON = "https://en.wikipedia.org/w/api.php?format=json" +
            "&action=query&prop=extracts&exintro=&explaintext=&titles=";
    private Scanner sc;
    private URLReader urlReader;

    public SearchWiki(){
        sc = new Scanner(System.in);
        urlReader = new URLReader();
    }

    public String run(String word){
        word = word.replaceAll("\\s", "%20");
        queryJSON = new StringBuilder().append(queryJSON).append(word).toString();
        String output = "";

        try {
            JSONObject jsonObject = urlReader.readJsonFromUrl(queryJSON);
            Map<String, Object> map = Mapper.convertJSONtoMap(jsonObject);

            String result = (String) map.get("extract");

            if(result == null) {
                output = "Your query is not correct. Try one more time";
            }
            else if(result.contains("refer to") || result.contains("may mean")){
                output = findRefers(result);
            }
            else {
                output = (String) map.get("extract");
            }

        } catch (IOException ex){
            ex.printStackTrace();
        }

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
