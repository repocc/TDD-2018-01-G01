package model;

import views.AlertView;
import websockets.JsonWebSocketLogger;
import websockets.Serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

    private String projectName;
    private String description;
    private List<String> states;
    private Map<String, ArrayList<Object>> stateFlow;
    private Map<String, String> rolesAssignment;
    private List<Ticket> tickets;

    public JsonWebSocketLogger Logger = JsonWebSocketLogger.getInstance();

    public Project(String name, String description){
        this.projectName = name;
        this.description = description;
        this.states = new ArrayList<>();
        this.stateFlow = new HashMap<String, ArrayList<Object>>();
        this.rolesAssignment = new HashMap<>();
        this.tickets = new ArrayList<>();
    }

    public String getProjectName(){
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void checkStates() {
        if (states.isEmpty()) {
            AlertView.createAlert("No se han agregado estados. Se agregara un estado inicial y uno final por defecto");
            states.add("Backlog");
            states.add("Done");
            stateFlow.put("Backlog", new ArrayList<>());
            stateFlow.get("Backlog").add("Done");
        }
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public Map<String, ArrayList<Object>> getStateFlow() {
        return stateFlow;
    }

    public void setStateFlow(Map<String, ArrayList<Object>> stateFlow) {
        this.stateFlow = stateFlow;
    }

    public Map<String, String> getRolesAssignment() {
        return rolesAssignment;
    }

    public void setRolesAssignment(Map<String, String> rolesAssignment) {
        this.rolesAssignment = rolesAssignment;
    }

    public void setValues(String name, String description) {

        this.setProjectName(name);
        this.setDescription(description);
    }

    public void addTicket(Ticket ticket) {
        Logger.publishData(Serializer.getJsonMetric(ticket));
        this.tickets.add(ticket);
    }
}


