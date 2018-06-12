package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ticket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TicketController {

    private static final Double HEIGHT_TICKET = 110.0;
    private static final Double WIDTH_TICKET = 90.0;
    private static final Double X_POSITION_TICKET = 58.0;
    private static final Double INITIAL_HEIGHT = 115.0;
    private static final Double SPACE_BETWEEN_TICKETS = 10.0;

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

    private Map<Integer, Ticket> tickets = new HashMap<>();
    private static int ticketCount = 0;
    private Double initialHeight = INITIAL_HEIGHT;


    public void createTicket() {
        this.editionMenu.setStyle("-fx-background-color: white;");
        this.createTicketLabel.setVisible(true);
        this.ticketName.setVisible(true);
        this.ticketType.setVisible(true);
        this.ticketDescription.setVisible(true);
        this.addResponsableButton.setVisible(true);
        this.ticketCreatedButton.setVisible(true);
    }

    public void openResponsableScreen() throws IOException {

        Parent responsablePage = FXMLLoader.load(getClass().getResource("../resources/responsable.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Asignacion de responsable");
        stage.setScene(new Scene(responsablePage));
        stage.show();
    }

    public void addTicket() {
        Ticket ticket = new Ticket(ticketName.getText());
        tickets.put((++ticketCount),ticket);
        Button ticketButton = new Button();
        ticketButton.setText(ticketName.getText());
        ticketButton.setPrefSize(WIDTH_TICKET, HEIGHT_TICKET);
        ticketButton.setLayoutX(X_POSITION_TICKET);
        ticketButton.setLayoutY(initialHeight + ticketButton.getPrefHeight() + SPACE_BETWEEN_TICKETS);
        initialHeight = ticketButton.getLayoutY();
        projectScreen.getChildren().add(ticketButton);
    }
}
