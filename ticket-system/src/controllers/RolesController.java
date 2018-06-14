package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import views.AlertView;

import java.util.HashMap;
import java.util.Map;

public class RolesController {

    private static Integer variationY = 15;

    @FXML
    private AnchorPane rolesAndUsersPane;
    @FXML
    private AnchorPane usersLeftPane;
    @FXML
    private AnchorPane rolesRightPane;
    @FXML
    private TextField rolesText;
    @FXML
    private Button applyConfigurationButton;


    private Integer positionYRoles = 0;
    private Integer positionYUsers = 0;
    private Integer positionYAssignment = 0;

    private Map<String, String> rolesAssignment = new HashMap<>();
    private String actualUser;
    private String actualRole;

    public void initData(Map<Integer, User> users) {
        for (int id: users.keySet()) {
            Label label = new Label();
            label.setText(users.get(id).getName());
            label.setLayoutY(positionYUsers);
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    label.setStyle("-fx-background-color: #4d4dff;");
                    actualUser = label.getText();
                }
            });
            positionYUsers += variationY;
            usersLeftPane.getChildren().add(label);
        }
    }

    public void addRole() {
        Label label = new Label();
        label.setText(rolesText.getText());
        label.setLayoutY(positionYRoles);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label.setStyle("-fx-background-color: #4d4dff;");
                actualRole = label.getText();
            }
        });
        positionYRoles += variationY;
        rolesRightPane.getChildren().add(label);
    }

    public void assignRoleToUser() {
        if (!rolesAssignment.containsKey(actualUser)) {
            Label label = new Label();
            label.setText(actualUser + " -> " + actualRole);
            label.setLayoutY(positionYAssignment);
            positionYAssignment += variationY;
            rolesAndUsersPane.getChildren().add(label);
            rolesAssignment.put(actualUser, actualRole);
        } else {
            AlertView.createAlert("El usuario " + actualUser + " ya tiene rol asignado");
        }
    }

    public void applyConfiguration() {
        if (rolesAssignment.isEmpty()) {
            AlertView.createAlert("Es requisito obligatorio agregar colaboradores y sus roles al proyecto");
        } else {
            Stage stage = (Stage) applyConfigurationButton.getScene().getWindow();
            stage.close();
        }
    }


    public Map<String, String> getRolesAssignment() {
        return rolesAssignment;
    }
}
