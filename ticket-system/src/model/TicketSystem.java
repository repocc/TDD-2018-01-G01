package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketSystem {

    private static TicketSystem instance;
    private Map<Integer, User> users;
    private List<Project> projects;

    public TicketSystem() {
        this.users = new HashMap<>();
        this.projects = new ArrayList<>();
    }

    public static TicketSystem getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new TicketSystem();
            return instance;
        }
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }
}
