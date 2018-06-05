package app;


import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private List<Project> projectsCreated;

    public User(String name) {
        this.name = name;
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
}
