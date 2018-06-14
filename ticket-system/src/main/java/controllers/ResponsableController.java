package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.AlertView;

import java.util.Map;

public class ResponsableController {

    private static Integer variationY = 15;

    private Integer positionYUsers = 0;
    private String actualUser;
    @FXML
    private AnchorPane projectUsers;
    @FXML
    private Button assignResponsableButton;


    public void assignResponsable() {
        if (this.actualUser != null) {
            Stage stage = (Stage) assignResponsableButton.getScene().getWindow();
            stage.close();
        } else {
            AlertView.createAlert("Debe asignar un responsable");
        }
    }

    public void initData(Map<String, String> users) {
        for (String user : users.keySet()) {
            final Label label = new Label();
            label.setText(user);
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
}
