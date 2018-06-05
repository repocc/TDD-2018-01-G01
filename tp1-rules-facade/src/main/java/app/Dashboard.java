package app;

import clojure.interop.ClojureInterop;
import clojure.interop.StateAndSignalsJson;

public class Dashboard {

    private String state;
    private String signals;

    public Dashboard(){
        this.initializeState();
    }

    private String initializeState() {
        this.state = ClojureInterop.initializeState();
        return this.state;
    }

    public String addRules(String rules){
        this.state = ClojureInterop.addRules(this.state, rules);
        return this.state;
    }

    public String getState(){
        return state;
    }

    public String getSignals() {
        return signals;
    }

    public synchronized String processData(String newData) {
        StateAndSignalsJson stateAndSignalsJson = ClojureInterop.processData(this.state, newData);
        this.state = stateAndSignalsJson.state;
        this.signals = stateAndSignalsJson.signals;
        return this.state;
    }

}
