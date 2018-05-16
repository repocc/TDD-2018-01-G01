package clojure.interop;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.PersistentArrayMap;

public class ClojureInterop {

    public static String initializeState() {

        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("clojure-java-interop"));

        IFn initialize  = Clojure.var("clojure-java-interop", "-initialize");

        String initializedStateMap = (String) initialize.invoke();

        return initializedStateMap;

    }



}
