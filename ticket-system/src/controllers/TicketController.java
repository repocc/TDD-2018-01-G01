package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Project;
import model.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketController {

    private static final Double HEIGHT_TICKET = 110.0;
    private static final Double WIDTH_TICKET = 90.0;
    private static final Double X_POSITION_TICKET = 58.0;
    private static final Double INITIAL_HEIGHT = 115.0;
    private static final Double SPACE_BETWEEN_TICKETS = 10.0;
    private static final Double SPACE_BETWEEN_TICKETS_X = 60.0;
    private static final Double Y_POSITION_LABEL = 150.0;
    private static final Double variationY = 15.0;

    @FXML
    private Label projectName;
    @FXML
    private AnchorPane projectScreen;
    @FXML
    private Pane editionMenu;
    @FXML
    private TextField ticketName;
    @FXML
    private TextArea ticketDescription;
    @FXML
    private TextField ticketType;
    @FXML
    private Label createTicketLabel;
    @FXML
    private Button addResponsableButton;
    @FXML
    private Button ticketCreatedButton;
    @FXML
    private Button createTicketButton;
    @FXML
    private Button saveChangesButton;

    private List<Ticket> tickets = new ArrayList<>();
    private static int ticketCount = 0;
    private Double initialHeight = INITIAL_HEIGHT;
    private Map<String, String> users;
    private Map<String, List<String>> stateFlow = new HashMap<>();
    private List<String> states = new ArrayList<>();
    private Double positionX = X_POSITION_TICKET;
    private String oldName;
    private Button actualButton;
    private Double positionYStates = 0.0;
    private String stateClicked;
    private List<Label> labelsStates = new ArrayList<>();

    public void createTicket() {
        this.editionMenu.setStyle("-fx-background-color: white;");
        this.createTicketLabel.setVisible(true);
        this.ticketName.setVisible(true);
        this.ticketName.setText("");
        this.ticketType.setVisible(true);
        this.ticketType.setText("");
        this.ticketDescription.setVisible(true);
        this.ticketDescription.setText("");
        this.addResponsableButton.setVisible(true);
        this.ticketCreatedButton.setVisible(true);
    }

    public void openResponsableScreen() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/responsable.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage appStage = new Stage();
        appStage.hide();
        appStage.setScene(scene);
        ResponsableController controller = loader.<ResponsableController>getController();
        controller.initData(users);
        appStage.show();

    }

    public void addTicket() {
        if (!ticketName.getText().equals("")) {
            Ticket ticket = new Ticket(ticketName.getText(), ticketDescription.getText(), ticketType.getText());
            ticket.setActualState(states.get(0));
            tickets.add(ticket);
            Button ticketButton = new Button();
            ticketButton.setText(ticketName.getText());
            ticketButton.setPrefSize(WIDTH_TICKET, HEIGHT_TICKET);
            ticketButton.setLayoutX(X_POSITION_TICKET);
            ticketButton.setLayoutY(initialHeight + ticketButton.getPrefHeight() + SPACE_BETWEEN_TICKETS);
            initialHeight = ticketButton.getLayoutY();
            projectScreen.getChildren().add(ticketButton);
            ticketButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        editTicket(ticket, ticketButton);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        createContextMenu(event, ticket, ticketButton);
                    }
                }

            });
            this.hideEditionMenu(this.ticketCreatedButton);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("El nombre del ticket es un campo obligatorio");
            alert.showAndWait();
        }
    }

    private void createContextMenu(MouseEvent event, Ticket ticket, Button button) {

        ContextMenu menu = new ContextMenu();
        MenuItem item1 = new MenuItem("Agregar comentario");
        MenuItem item2 = new MenuItem("Cambiar estado");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event2) {
                addCommentScreen(ticket);
            }
        });

        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event2) {
               changeState(stateFlow, ticket, button);
            }
        });

        menu.getItems().addAll(item1, item2);
        menu.show(button, event.getScreenX(), event.getScreenY());

    }

    private void changeState(Map<String, List<String>> stateFlow, Ticket ticket, Button buttonTicket) {
        AnchorPane pane = new AnchorPane();
        Button button = new Button();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        pane.setPrefSize(80.0, 150.0);
        pane.setStyle("-fx-background-color: white;");
        button.setLayoutX(0);
        button.setLayoutY(pane.getPrefHeight());
        button.setPrefSize(80.0, 25.0);
        button.setText("Cambiar estado");
        this.showStatesToGo(stateFlow, ticket.getActualState(), pane);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ticket.setActualState(stateClicked);
                pane.setVisible(false);
                button.setVisible(false);
                moveTicket(buttonTicket);
            }
        });
        projectScreen.getChildren().addAll(pane, button);
    }

    private void moveTicket(Button button) {

        for (Label label : labelsStates) {
            if (label.getText().equals(stateClicked)) {
                button.setLayoutX(label.getLayoutX());
            }
        }

    }

    private void showStatesToGo(Map<String, List<String>> stateFlow, String actualState, Pane pane) {
        if (stateFlow.containsKey(actualState)) {
            List<String> possibleStates = stateFlow.get(actualState);
            positionYStates = 0.0;
            for (String state : possibleStates) {
                Label label = new Label();
                label.setText(state);
                label.setLayoutY(positionYStates);
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        label.setStyle("-fx-background-color: #4d4dff;");
                        stateClicked = label.getText();
                    }
                });
                positionYStates += variationY;
                pane.getChildren().add(label);
            }
        }
    }

    private void addCommentScreen(Ticket ticket) {
        TextArea textArea = new TextArea();
        Button button = new Button();
        textArea.setPromptText("Escriba su comentario...");
        textArea.setPrefSize(200.0, 60.0);
        textArea.setLayoutX(0);
        textArea.setLayoutY(0);
        button.setText("Comentar");
        button.setPrefSize(200.0, 30.0);
        button.setLayoutX(0);
        button.setLayoutY(textArea.getPrefHeight());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ticket.addComment(textArea.getText());
                textArea.setVisible(false);
                button.setVisible(false);
            }
        });
        projectScreen.getChildren().addAll(textArea, button);

    }

    private void editTicket(Ticket ticket, Button button) {

        this.oldName = ticket.getTitle();
        editionMenu.setStyle("-fx-background-color: white;");
        this.createTicketLabel.setVisible(true);
        this.ticketName.setVisible(true);
        this.ticketType.setVisible(true);
        this.ticketDescription.setVisible(true);
        this.addResponsableButton.setVisible(true);
        this.saveChangesButton.setVisible(true);
        this.setFields(ticket);
        actualButton = button;
    }

    private void setFields(Ticket ticket) {
        this.ticketName.setText(ticket.getTitle());
        this.ticketDescription.setText(ticket.getDescription());
        this.ticketType.setText(ticket.getType());
    }

    private void hideEditionMenu(Button button) {
        this.editionMenu.setStyle("-fx-background-color: green;");
        this.createTicketLabel.setVisible(false);
        this.ticketName.setVisible(false);
        this.ticketType.setVisible(false);
        this.ticketDescription.setVisible(false);
        this.addResponsableButton.setVisible(false);
        button.setVisible(false);
    }

    public void initData(String projectName, Map<String, String> users, List<String> states, Map<String, List<String>> stateFlow) {
        this.projectName.setText(projectName);
        this.users = users;
        this.stateFlow = stateFlow;
        this.states = states;
        this.showStates();
    }

    private void showStates() {
        for (String state: states) {
            Label label = new Label();
            label.setText(state);
            label.setStyle("-fx-font-size: 18;");
            label.setLayoutX(positionX);
            label.setLayoutY(Y_POSITION_LABEL);
            positionX += WIDTH_TICKET + SPACE_BETWEEN_TICKETS_X;
            labelsStates.add(label);
            projectScreen.getChildren().add(label);
        }
    }

    public void saveChanges() {
        if (!ticketName.getText().equals("")) {
            for (Ticket ticket : tickets) {
                if (ticket.getTitle().equals(oldName)) {
                    ticket.setTitle(projectName.getText());
                    ticket.setDescription(ticketDescription.getText());
                    ticket.setType(ticketType.getText());
                }
            }
            actualButton.setText(ticketName.getText());
            this.hideEditionMenu(saveChangesButton);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("El nombre del ticket es un campo obligatorio");
            alert.showAndWait();
        }
    }
}
