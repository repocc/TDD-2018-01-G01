package tdd.tp.controller;

import app.RulesValidatorApp;

public class Accesor {

    public synchronized String processDataAndReturnSignals(String newData) {

        RulesValidatorApp rt = RulesValidatorApp.getInstance();
        String processed = rt.processData(newData);
        System.out.println(newData);
        return rt.getSignals("default");

    }
}
