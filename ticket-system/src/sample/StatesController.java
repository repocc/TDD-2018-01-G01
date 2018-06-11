package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class StatesController {

    private static Integer variationY = 15;

    @FXML
    private AnchorPane stateFlowPane;
    @FXML
    private AnchorPane statesShowPaneLeft;
    @FXML
    private AnchorPane statesShowPaneRight;
    @FXML
    private TextField statesText;
    @FXML
    private Button addState;
    @FXML
    private Button applyStatesFlow;
    private Integer positionY = 0;
    private List<String> states = new ArrayList<>();

    public void setRole() {
        Label labelLeft = new Label();
        Label labelRight = new Label();
        labelLeft.setText(statesText.getText());
        labelRight.setText(statesText.getText());
        labelLeft.setLayoutY(positionY);
        labelRight.setLayoutY(positionY);
        positionY += variationY;
        statesShowPaneLeft.getChildren().add(labelLeft);
        statesShowPaneRight.getChildren().add(labelRight);
        states.add(statesText.getText());
    }

    public void setStatesFlow() {
        if (states.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("No se han agregado estados. Se agregara un estado inicial y uno final por defecto");
            alert.showAndWait();
            states.add("Backlog");
            states.add("Done");
        } else {

        }
        Stage stage = (Stage) applyStatesFlow.getScene().getWindow();
        stage.close();
    }

}
