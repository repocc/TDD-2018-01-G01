package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Project;
import model.User;

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
    private Map<Integer, Project> projects = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();
    private Map<String, String> rolesAssignment = new HashMap<>();
    private static int projectCount = 0;
    private Double initialHeight = INITIAL_HEIGHT;

    public void createProject() {
        this.editionMenu.setStyle("-fx-background-color: white;");
        this.createProject.setVisible(true);
        this.projectName.setVisible(true);
        this.projectName.setText("");
        this.projectDescription.setVisible(true);
        this.projectDescription.setText("");
        this.states.setVisible(true);
        this.projectUsers.setVisible(true);
        this.projectFinished.setVisible(true);
    }

    public void addProject() {
        if (!projectName.getText().equals("")) {
            Project project = new Project(projectName.getText());
            projects.put((++projectCount), project);
            Button projectButton = new Button();
            projectButton.setText(projectName.getText());
            projectButton.setPrefSize(WIDTH_PROJECT, HEIGHT_PROJECT);
            projectButton.setLayoutX(X_POSITION_PROJECT);
            projectButton.setLayoutY(initialHeight + projectButton.getPrefHeight() + SPACE_BETWEEN_PROJECTS);
            initialHeight = projectButton.getLayoutY();
            projectScreen.getChildren().add(projectButton);
            projectButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FXMLLoader projectPage = new FXMLLoader(getClass().getResource("../resources/ticket.fxml"));
                    Scene projectScene = null;
                    try {
                        projectScene = new Scene(projectPage.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.hide();
                    appStage.setScene(projectScene);
                    TicketController controller = projectPage.<TicketController>getController();
                    controller.initData(project.getProjectName(), rolesAssignment);
                    appStage.show();
                }
            });
            this.hideEditionMenu();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("El nombre del proyecto es un campo obligatorio");
            alert.showAndWait();
        }
    }

    private void hideEditionMenu() {
        this.editionMenu.setStyle("-fx-background-color: green;");
        this.createProject.setVisible(false);
        this.projectName.setVisible(false);
        this.projectDescription.setVisible(false);
        this.states.setVisible(false);
        this.projectUsers.setVisible(false);
        this.projectFinished.setVisible(false);
    }

    public void openStatesWindow() throws IOException {
        Parent statesPage = FXMLLoader.load(getClass().getResource("../resources/states.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Flujo de estados de tickets");
        stage.setScene(new Scene(statesPage));
        stage.show();
    }

    public void openRolesWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/roles.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage appStage = new Stage();
        appStage.hide();
        appStage.setScene(scene);
        RolesController controller = loader.<RolesController>getController();
        controller.initData(users);
        appStage.showAndWait();
        this.rolesAssignment = controller.getRolesAssignment();

    }

    public void initData(Map<Integer, User> users) {
        this.users = users;
    }
}
