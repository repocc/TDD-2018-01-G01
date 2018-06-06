package tdd.tp.controller;

import app.RulesValidatorApp;

public class Accesor {

    public static synchronized String processDataAndReturnSignals(String newData, String dashboard) {
        RulesValidatorApp rt = RulesValidatorApp.getInstance();
        rt.processData(newData);
        return rt.getSignals(dashboard);
    }
}
