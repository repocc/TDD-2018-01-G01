package clojure.interop;

public class StateAndSignalsJson {

    public StateAndSignalsJson(String jsonStringState, String jsonStringSignal) {
        this.signals = jsonStringSignal;
        this.state = jsonStringState;
    }

    //Both in json format.
    public String state;
    public String signals;

}
