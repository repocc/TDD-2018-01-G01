package app;

import app.RulesValidatorApp;

public class MainClass {

    public static void main(String args[]) throws Exception {

        RulesValidatorApp rules = RulesValidatorApp.getInstance();
        rules.initializeState();

        System.out.println("ñam");

    }
}
