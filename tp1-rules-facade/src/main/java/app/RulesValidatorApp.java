package app;

import clojure.interop.ClojureInterop;
import org.springframework.stereotype.Service;

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

    private String initializeState() {
        this.state = ClojureInterop.initializeState();
        return this.state;
    }

    public void addRule(){
        //todo
    }

    public String getState(){
        return state;
    }

    public synchronized String processData(String newData) {
        this.state = ClojureInterop.processData(this.state, newData);
        return this.state;
    }

}
