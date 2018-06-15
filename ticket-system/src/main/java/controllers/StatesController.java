package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.AlertView;

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
    private Button applyStatesFlowButton;

    private Integer positionYStates = 0;
    private Integer positionYFlow = 0;
    private List<String> states = new ArrayList<>();
    private Map<String, ArrayList<Object>> stateFlow = new HashMap<String, ArrayList<Object>>();
    private String leftState;
    private String rightState;

    public void addState() {
        final Label labelLeft = new Label();
        final Label labelRight = new Label();
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
            AlertView.createAlert("No se han agregado estados. Se agregara un estado inicial y uno final por defecto");
            states.add("Backlog");
            states.add("Done");
            stateFlow.put("Backlog", new ArrayList<>());
            stateFlow.get("Backlog").add("Done");
        }
        Stage stage = (Stage) applyStatesFlowButton.getScene().getWindow();
        stage.close();
    }

    public Map<String, ArrayList<Object>> getStateFlow() {
        return this.stateFlow;
    }

    public List<String> getStates() {
        return states;
    }
}