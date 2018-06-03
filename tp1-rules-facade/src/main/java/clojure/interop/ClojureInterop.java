package clojure.interop;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class ClojureInterop {

    private static IFn getClojureFunction(String name){
        String clojurePackage = "clojure-java-interop";
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read(clojurePackage));
        IFn function  = Clojure.var(clojurePackage, name);
        return function;
    }

    public static String initializeState() {
        IFn initialize = getClojureFunction("-initialize");
        return (String) initialize.invoke();
    }

    public static StateAndSignalsJson processData(String state, String data) {
        IFn processData = getClojureFunction("-process-data");
        clojure.lang.PersistentVector stateAndSignals = (clojure.lang.PersistentVector) processData.invoke(state, data);
        StateAndSignalsJson stateAndSignalsJson = new StateAndSignalsJson((String) stateAndSignals.get(0), (String) stateAndSignals.get(1));
        return stateAndSignalsJson;
    }

    public static String addRules(String state, String rule) {
        IFn processData = getClojureFunction("-add-rule");
        String newState = (String) processData.invoke(state, rule);
        return newState;
    }

}
