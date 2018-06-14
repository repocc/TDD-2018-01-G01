package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String password;
    private List<Project> projectsCreated;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.projectsCreated = new ArrayList<Project>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProjectCreated(Project project) {
        this.projectsCreated.add(project);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
