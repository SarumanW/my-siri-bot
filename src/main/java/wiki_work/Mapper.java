package wiki_work;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Mapper {
    private static Map<String, Object> map = new HashMap<>();

    public static Map<String, Object> convertJSONtoMap(JSONObject jsonObject) throws IOException{
        Iterator<?> keys = jsonObject.keys();

        while(keys.hasNext()) {
            String key = (String)keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                convertJSONtoMap((JSONObject) jsonObject.get(key));
            }
            else{
                map.put(key, jsonObject.get(key));
            }
        }
        return map;
    }



}
