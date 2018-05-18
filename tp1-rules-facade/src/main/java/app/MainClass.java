package app;

import app.RulesValidatorApp;

public class MainClass {

    public static void main(String args[]) throws Exception {

        RulesValidatorApp rules = RulesValidatorApp.getInstance();
        String state = rules.initializeState();

        String data0 = "{\"name\":\"John\",\"age\":30,\"sender\":true}";
        String data1 = "{\"name\":\"John\",\"age\":30,\"important\":true}";
        String data2 = "{\"name\":\"John\",\"age\":30,\"spam\":true}";

        rules.processData(data0);
        rules.processData(data1);
        rules.processData(data2);

        System.out.println("ñam");

    }
}
