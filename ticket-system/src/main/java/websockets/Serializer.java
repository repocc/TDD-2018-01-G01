package websockets;

import com.google.gson.Gson;
import model.Ticket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Serializer {

    public static String getJsonMetric(Ticket ticket) {
        Map<String,String> mapValues = new HashMap<>();
        mapValues.put("state",ticket.getActualState());
        mapValues.put("type", ticket.getType());
        return mapToJsonString(mapValues);
    }

    public static String logUserLoggin(String user){
        Map<String,String> mapValues = new HashMap<>();
        mapValues.put("user",user);
        mapValues.put("date", "2018-06-13");
        return mapToJsonString(mapValues);

    }

    private static String mapToJsonString(Map<String,String> values){
        Gson gson = new Gson();
        String json = gson.toJson(values);
        return  json;
    }
}
