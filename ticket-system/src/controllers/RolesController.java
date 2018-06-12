package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Parser;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Button addRoleButton;
    @FXML
    private Button assignRoleToUserButton;
    @FXML
    private Button applyConfigurationButton;

    private Integer positionYRoles = 0;
    private Integer positionYUsers = 0;
    private Integer positionYAssignment = 0;
    private List<String> roles = new ArrayList<>();
    private Map<Integer, User> users;
    private Map<String, String> rolesAssignment = new HashMap<>();
    private String actualUser;
    private String actualRole;

    @FXML
    public void initialize() {
        Parser user_parser = new Parser();
        users = user_parser.parseUsersList();
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
        roles.add(rolesText.getText());
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("El usuario " + actualUser + " ya tiene rol asignado");
            alert.showAndWait();
        }
    }

    public void applyConfiguration() {
        if (rolesAssignment.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Es requisito obligatorio agregar colaboradores y sus roles al proyecto");
            alert.showAndWait();
        } else {
            Stage stage = (Stage) applyConfigurationButton.getScene().getWindow();
            stage.close();
        }
    }
}
