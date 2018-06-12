package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Parser;
import model.User;

import java.util.Map;

public class ResponsableController {

    private static Integer variationY = 15;

    private Map<Integer, User> users;
    private Integer positionYUsers = 0;
    private String actualUser;
    private String responsable;
    @FXML
    private AnchorPane projectUsers;
    @FXML
    private Button assignResponsableButton;

    @FXML
    public void initialize() {
        Parser user_parser = new Parser();
        users = user_parser.parseUsersList();
        for (int id : users.keySet()) {
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
            projectUsers.getChildren().add(label);
        }
    }

    public void assignResponsable() {
        if (this.actualUser != null) {
            this.responsable = this.actualUser;
            Stage stage = (Stage) assignResponsableButton.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Debe asignar un responsable");
            alert.showAndWait();
        }
    }

}
