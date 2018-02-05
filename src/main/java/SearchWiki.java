import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SearchWiki {
    private String queryJSON = "https://en.wikipedia.org/w/api.php?format=json" +
            "&action=query&prop=extracts&exintro=&explaintext=&titles=";
    private Scanner sc;
    private URLReader urlReader;

    SearchWiki(){
        sc = new Scanner(System.in);
        urlReader = new URLReader();
    }

    public void run(){
        System.out.print("Type the word you want to search - ");
        String word = sc.nextLine();

        word = word.replaceAll("\\s", "%20");
        queryJSON = new StringBuilder().append(queryJSON).append(word).toString();

        try {
            JSONObject jsonObject = urlReader.readJsonFromUrl(queryJSON);
            Map<String, Object> map = Mapper.convertJSONtoMap(jsonObject);

            String result = (String) map.get("extract");
            if(result==null) {
                System.out.println("Your query is not correct. Try one more time");
                return;
            }
            else if(result.contains("refer to") || result.contains("may mean")){
                findRefers(result);
                return;
            }
            else {
                System.out.println("The meaning of your word in wiki:");
                System.out.println(map.get("extract"));
//                map.forEach((k, v) ->
//                        System.out.println(k + " = " + v));
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void findRefers(String result) throws IOException{
        String[] array = result.split("\n");
        for(int i = 1; i<array.length; i++){
            System.out.println(array[i]);
        }
    }
}
