package app;
public class RulesValidatorApp {

    private static RulesValidatorApp instance;

    private RulesValidatorApp() {
    }

    public static RulesValidatorApp getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new RulesValidatorApp();
            return instance;
        }
    }

    public void initializeState(String rules) {
        //todo
    }

    public void addRule(){
        //todo
    }

    public void getState(){
        //todo
    }

    public void processData(String newData) {
        //todo
    }

    public void getQueryCounter(String counterName, String[] params, boolean[] values) {
        //todo
    }


}