package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProjectController {

    private static final Double SPACE_BETWEEN_PROJECTS = 10.0;
    private static final Double X_POSITION_PROJECT = 58.0;
    private static final Double WIDTH_PROJECT = 300.0;
    private static final Double HEIGHT_PROJECT = 30.0;
    private static final Double INITIAL_HEIGHT = 115.0;

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
    private Double initialHeight = INITIAL_HEIGHT;

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
        projectButton.setPrefSize(WIDTH_PROJECT, HEIGHT_PROJECT);
        projectButton.setLayoutX(X_POSITION_PROJECT);
        projectButton.setLayoutY(initialHeight + projectButton.getPrefHeight() + SPACE_BETWEEN_PROJECTS);
        initialHeight = projectButton.getLayoutY();
        projectScreen.getChildren().add(projectButton);
    }

    public void openStatesWindow() throws IOException {
        Parent statesPage = FXMLLoader.load(getClass().getResource("states.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Flujo de estados de tickets");
        stage.setScene(new Scene(statesPage));
        stage.show();
    }
}
