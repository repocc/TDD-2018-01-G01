package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

    private String projectName;
    private String description;
    private List<String> states;
    private Map<String, List<String>> stateFlow;
    private Map<String, String> rolesAssignment;
    private List<Ticket> tickets;

    public Project(String name, String description){
        this.projectName = name;
        this.description = description;
        this.states = new ArrayList<>();
        this.stateFlow = new HashMap<>();
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
            states.add("Backlog");
            states.add("Finished");
        }
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public Map<String, List<String>> getStateFlow() {
        return stateFlow;
    }

    public void setStateFlow(Map<String, List<String>> stateFlow) {
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
        this.tickets.add(ticket);
    }
}


