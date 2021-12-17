package chatting_app_server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject; //JSONObject 객체 
import org.json.simple.parser.JSONParser; //JSON 객체 파싱

public class JSONAdd {
    String json;
    
    void jsonadd() {
    	JSONObject inner=new JSONObject();
    	inner.put("hi", "hi");
    	inner.put("how are you?", "i'm fine");
    	inner.put("who are you?", "i'm server");
    	inner.put("what are you doing?", "i'm chatting");
    	inner.put("ok bye", "good bye");
    	
    	JSONObject outer=new JSONObject();
    	outer.put("inf", inner);
    	
    	json=outer.toJSONString();
    }
}
