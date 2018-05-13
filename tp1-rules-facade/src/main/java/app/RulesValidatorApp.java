package app;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class RulesValidatorApp {

    private static RulesValidatorApp instance;

    private RulesValidatorApp() {
    }

    public static RulesValidatorApp getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new RulesValidatorApp();
            return instance;
        }
    }

    public void initializeState(String rules) {
        //todo
    }

    public void addRule(){
        //todo
    }

    public String getState(){
        return ("Hola");
    }

    public String processData(Map<String, String> newData) {
        return newData.get("important");
    }

    public void getQueryCounter(String counterName, String[] params, boolean[] values) {
        //todo
    }


}
