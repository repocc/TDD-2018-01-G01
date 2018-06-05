package app;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RulesValidatorApp {

    private static RulesValidatorApp instance;
    private static Map<String, Dashboard> dashboards = new HashMap<String, Dashboard>();


    private RulesValidatorApp() {
        dashboards.put("default", new Dashboard());
    }

    public static RulesValidatorApp getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new RulesValidatorApp();
            return instance;
        }
    }

    public String addRules(String rules, String idDashboard){
        return this.dashboards.get(idDashboard).addRules(rules);
    }

    public String getState(String idDashboard){
        return dashboards.get(idDashboard).getState();
    }

    public String getSignals(String idDashboard) {
        return dashboards.get(idDashboard).getSignals();
    }

    public synchronized String processData(String newData) {
        for(Dashboard d : dashboards.values()) {
            d.processData(newData);
        }
        return newData;
    }

    public void addDashboard(String idDashboard) {
        dashboards.put(idDashboard, new Dashboard());
    }

    public Set<String> getDashboardsIds() {
        return this.dashboards.keySet();
    }



}
