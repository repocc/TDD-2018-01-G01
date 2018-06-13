package model;


public class Project {

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String projectName;
    private String description;


    public Project(String name, String description){
        this.projectName = name;
        this.description = description;
    }


    public String getProjectName(){
        return projectName;
    }


    public String getDescription() {
        return description;
    }
}


