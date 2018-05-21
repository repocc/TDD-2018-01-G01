package clojure.interop;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.PersistentArrayMap;

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

    public static String processData(String state, String data) {
        IFn processData = getClojureFunction("-process-data");
        String newState = (String) processData.invoke(state, data);
        return newState;
    }

    public static String addRules(String state, String rule) {
        IFn processData = getClojureFunction("-add-rule");
        String newState = (String) processData.invoke(state, rule);
        return newState;
    }



}
