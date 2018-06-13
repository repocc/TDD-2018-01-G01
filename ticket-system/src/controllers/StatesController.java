package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private Button addStateButton;
    @FXML
    private Button applyStatesFlowButton;
    @FXML
    private Button addFlowButton;

    private Integer positionYStates = 0;
    private Integer positionYFlow = 0;
    private List<String> states = new ArrayList<>();
    private Map<String, List<String>> stateFlow = new HashMap<>();
    private String leftState;
    private String rightState;

    public void addState() {
        Label labelLeft = new Label();
        Label labelRight = new Label();
        labelLeft.setText(statesText.getText());
        labelRight.setText(statesText.getText());
        labelLeft.setLayoutY(positionYStates);
        labelRight.setLayoutY(positionYStates);
        labelLeft.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                labelLeft.setStyle("-fx-background-color: #4d4dff;");
                leftState = labelLeft.getText();
            }
        });
        labelRight.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                labelRight.setStyle("-fx-background-color: #4d4dff;");
                rightState = labelRight.getText();
            }
        });

        positionYStates += variationY;
        statesShowPaneLeft.getChildren().add(labelLeft);
        statesShowPaneRight.getChildren().add(labelRight);
        states.add(statesText.getText());
    }

    public void addFlow() {
        if (leftState != null && rightState != null) {
            if (stateFlow.containsKey(leftState)) {
                stateFlow.get(leftState).add(rightState);
            } else {
                stateFlow.put(leftState, new ArrayList<>());
                stateFlow.get(leftState).add(rightState);
            }
        }
        this.addValueToStateFlowPane();
    }

    private void addValueToStateFlowPane() {
        Label label = new Label();
        label.setText(leftState + " -> " + rightState);
        label.setLayoutY(positionYFlow);
        positionYFlow += variationY;
        stateFlowPane.getChildren().add(label);
    }

    public void applyStatesFlow() {
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
        Stage stage = (Stage) applyStatesFlowButton.getScene().getWindow();
        stage.close();
    }

    public Map<String,List<String>> getStateFlow() {
        return this.stateFlow;
    }

    public List<String> getStates() {
        return states;
    }
}
