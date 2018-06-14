package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ProjectView {

    private static final Double SPACE_BETWEEN_PROJECTS = 10.0;
    private static final Double X_POSITION_PROJECT = 58.0;
    private static final Double WIDTH_PROJECT = 300.0;
    private static final Double HEIGHT_PROJECT = 30.0;
    private static final Double INITIAL_HEIGHT = 115.0;

    private AnchorPane projectScreen;
    private Pane editionMenu;
    private Label createProject;
    private TextField projectName;
    private TextArea projectDescription;
    private Button states;
    private Button projectUsers;
    private Button projectFinished;
    private Button saveChangesButton;
    private List<Button> projectButtons;
    private Double initialHeight = INITIAL_HEIGHT;

    private static ProjectView instance;

    public ProjectView() {
        this.projectButtons = new ArrayList<>();
    }

    public static ProjectView getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new ProjectView();
            return instance;
        }
    }

    public AnchorPane getProjectScreen() {
        return projectScreen;
    }

    public void setProjectScreen(AnchorPane projectScreen) {
        this.projectScreen = projectScreen;
    }

    public Pane getEditionMenu() {
        return editionMenu;
    }

    public void setEditionMenu(Pane editionMenu) {
        this.editionMenu = editionMenu;
    }

    public Label getCreateProject() {
        return createProject;
    }

    public void setCreateProject(Label createProject) {
        this.createProject = createProject;
    }

    public TextField getProjectName() {
        return projectName;
    }

    public void setProjectName(TextField projectName) {
        this.projectName = projectName;
    }

    public TextArea getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(TextArea projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Button getStates() {
        return states;
    }

    public void setStates(Button states) {
        this.states = states;
    }

    public Button getProjectUsers() {
        return projectUsers;
    }

    public void setProjectUsers(Button projectUsers) {
        this.projectUsers = projectUsers;
    }

    public Button getProjectFinished() {
        return projectFinished;
    }

    public void setProjectFinished(Button projectFinished) {
        this.projectFinished = projectFinished;
    }

    public Button getSaveChangesButton() {
        return saveChangesButton;
    }

    public void setSaveChangesButton(Button saveChangesButton) {
        this.saveChangesButton = saveChangesButton;
    }

    public void setVisibility(String stylePane, boolean visibility, Button button) {
        this.editionMenu.setStyle(stylePane);
        this.createProject.setVisible(visibility);
        this.projectName.setVisible(visibility);
        this.projectDescription.setVisible(visibility);
        this.states.setVisible(visibility);
        this.projectUsers.setVisible(visibility);
        button.setVisible(visibility);
    }

    public void setTexts() {
        this.projectName.setText("");
        this.projectDescription.setText("");
    }

    public void addProject(Button projectButton) {
        projectButton.setText(projectName.getText());
        projectButton.setPrefSize(WIDTH_PROJECT, HEIGHT_PROJECT);
        projectButton.setLayoutX(X_POSITION_PROJECT);
        projectButton.setLayoutY(initialHeight + projectButton.getPrefHeight() + SPACE_BETWEEN_PROJECTS);
        initialHeight = projectButton.getLayoutY();
        projectScreen.getChildren().add(projectButton);
        this.projectButtons.add(projectButton);
    }
}
