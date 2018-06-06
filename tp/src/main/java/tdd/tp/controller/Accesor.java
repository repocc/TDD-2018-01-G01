package tdd.tp.controller;

import app.RulesValidatorApp;

public class Accesor {

    public synchronized String processDataAndReturnSignals(String newData, String dashboard) {

        RulesValidatorApp rt = RulesValidatorApp.getInstance();
        String processed = rt.processData(newData);
        System.out.println(newData);
        return rt.getSignals(dashboard);

    }
}
