package app;

public class MainClass {

    public static void main(String args[]) throws Exception {

        RulesValidatorApp rules = RulesValidatorApp.getInstance();
        String state = rules.getState();

        //String rule1 = "[[\"define-counter\",\"spam-count\",[],[\"current\",\"spam\"]]]";
        //String rule2 = "[[\"define-signal\",{\"spam-fraction\":[\"\\/\",[\"counter-value\",\"spam-count\",[]],[\"counter-value\",\"email-count\",[]]]},true]]";

        //rules.addRules(rule1);

        String data0 = "{\"name\":\"John\",\"age\":30,\"sender\":true}";
        String data1 = "{\"name\":\"John\",\"age\":30,\"important\":true}";
        String data2 = "{\"name\":\"John\",\"age\":30,\"spam\":true}";

        state = rules.processData(data0);
        state = rules.processData(data1);
        state = rules.processData(data2);

        System.out.println(state);

        System.out.println("ñam");

    }
}
