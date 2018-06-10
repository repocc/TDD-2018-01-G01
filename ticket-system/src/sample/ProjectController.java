package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProjectController {

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


    public void createProject() {
        this.editionMenu.setStyle("-fx-background-color: white;");
        this.createProject.setVisible(true);
        this.projectName.setVisible(true);
        this.projectDescription.setVisible(true);
        this.states.setVisible(true);
        this.projectUsers.setVisible(true);
        this.projectFinished.setVisible(true);
    }
}
