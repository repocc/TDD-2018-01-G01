package app;

import clojure.interop.ClojureInterop;
import clojure.lang.PersistentArrayMap;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RulesValidatorApp {

    private static RulesValidatorApp instance;
    private String state;

    private RulesValidatorApp() {
        this.initializeState();
    }

    public static RulesValidatorApp getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new RulesValidatorApp();
            return instance;
        }
    }

    public String initializeState() {
        this.state = ClojureInterop.initializeState();
        return this.state;
    }

    public void addRule(){
        //todo
    }

    public String getState(){
        return state;
    }

    public String processData(Map<String, String> newData) {
        return newData.get("important");
    }

    public void getQueryCounter(String counterName, String[] params, boolean[] values) {
        //todo
    }


}
