package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class ProjectController {

    private static final Double SPACE_BETWEEN_PROJECTS = 10.0;

    @FXML
    private AnchorPane projectScreen;
    @FXML
    private Pane editionMenu;
    @FXML
    private Label createProject;
    @FXML
    private TextField projectName;
    @FXML
    private TextArea projectDescription;
    @FXML
    private Button states;
    @FXML
    private Button projectUsers;
    @FXML
    private Button projectFinished;
    private Map<Integer,Project> projects = new HashMap<>();
    private static int projectCount = 0;
    private Double initialHeight = 115.0;

    public void createProject() {
        this.editionMenu.setStyle("-fx-background-color: white;");
        this.createProject.setVisible(true);
        this.projectName.setVisible(true);
        this.projectDescription.setVisible(true);
        this.states.setVisible(true);
        this.projectUsers.setVisible(true);
        this.projectFinished.setVisible(true);
    }

    public void addProject() {
        Project project = new Project(projectName.getText());
        projects.put((++projectCount),project);
        Button projectButton = new Button();
        projectButton.setText(projectName.getText());
        projectButton.setPrefSize(300, 30);
        projectButton.setLayoutX(58);
        projectButton.setLayoutY(initialHeight + projectButton.getPrefHeight() + SPACE_BETWEEN_PROJECTS);
        initialHeight = projectButton.getLayoutY();
        projectScreen.getChildren().add(projectButton);
    }
}
