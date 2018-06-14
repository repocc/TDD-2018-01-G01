package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Project;
import model.TicketSystem;
import model.User;
import views.AlertView;
import views.ProjectView;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ProjectController {

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
    @FXML
    private Button saveChangesButton;

    private TicketSystem system;
    private ProjectView view;
    private Project actualProject;
    private Button actualButton;

    public void initData(Map<Integer, User> users) {
        this.system = TicketSystem.getInstance();
        this.system.setUsers(users);
        this.view = ProjectView.getInstance();
        this.view.setProjectScreen(projectScreen);
        this.view.setEditionMenu(editionMenu);
        this.view.setCreateProject(createProject);
        this.view.setProjectName(projectName);
        this.view.setProjectDescription(projectDescription);
        this.view.setStates(states);
        this.view.setProjectUsers(projectUsers);
        this.view.setProjectFinished(projectFinished);
        this.view.setSaveChangesButton(saveChangesButton);
    }

    public void createProject() {
        this.actualProject = new Project(projectName.getText(), projectDescription.getText());
        this.view.setVisibility("-fx-background-color: white;", true, projectFinished);
        this.view.setTexts();
    }

    public void addProject() {
        if (!projectName.getText().equals("")) {
            actualProject.setProjectName(projectName.getText());
            actualProject.setDescription(projectDescription.getText());
            system.addProject(actualProject);
            final Button projectButton = new Button();
            final Project project = actualProject;
            this.view.addProject(projectButton);
            projectButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        openTicketWindow(project, event);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        createContextMenu(event, project, projectButton);
                    }
                }

            });
            actualProject.checkStates();
            this.hideEditionMenu(projectFinished);
        } else {
            AlertView.createAlert("El nombre del proyecto es un campo obligatorio");
        }
    }

    private void createContextMenu(MouseEvent event, final Project project, final Button button) {
        ContextMenu menu = new ContextMenu();
        MenuItem item1 = new MenuItem("Editar");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                actualProject = project;
                view.setVisibility("-fx-background-color: white;", true, saveChangesButton);
                setFields(project);
                actualButton = button;
            }
        });
        menu.getItems().addAll(item1);
        menu.show(button, event.getScreenX(), event.getScreenY());

    }

    private void openTicketWindow(Project project, MouseEvent event) {
        FXMLLoader projectPage = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("ticket.fxml")));
        Scene projectScene = null;
        try {
            projectScene = new Scene((Parent) projectPage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(projectScene);
        TicketController controller = projectPage.<TicketController>getController();
        controller.initData(project);
        appStage.show();
    }

    private void hideEditionMenu(Button button) {
        this.view.setVisibility("-fx-background-color: green;", false, button);
    }

    public void openStatesWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("states.fxml")));
        Scene scene = null;
        try {
            scene = new Scene((Parent) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage appStage = new Stage();
        appStage.hide();
        appStage.setScene(scene);
        StatesController controller = loader.<StatesController>getController();
        appStage.showAndWait();
        actualProject.setStates(controller.getStates());
        actualProject.setStateFlow(controller.getStateFlow());
    }

    public void openRolesWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("roles.fxml")));
        Scene scene = null;
        try {
            scene = new Scene((Parent) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage appStage = new Stage();
        appStage.hide();
        appStage.setScene(scene);
        RolesController controller = loader.<RolesController>getController();
        controller.initData(system.getUsers());
        appStage.showAndWait();
        this.actualProject.setRolesAssignment(controller.getRolesAssignment());

    }

    public void setFields(Project project) {
        this.projectName.setText(project.getProjectName());
        this.projectDescription.setText(project.getDescription());
    }

    public void saveChanges() {
        if (!projectName.getText().equals("")) {
            this.actualProject.setValues(projectName.getText(), projectDescription.getText());
            actualButton.setText(projectName.getText());
            this.hideEditionMenu(saveChangesButton);
        } else {
            AlertView.createAlert("El nombre del proyecto es un campo obligatorio");
        }
    }
}
