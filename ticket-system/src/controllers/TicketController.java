package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ticket;
import model.User;

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

    private Map<Integer, Ticket> tickets = new HashMap<>();
    private static int ticketCount = 0;
    private Double initialHeight = INITIAL_HEIGHT;
    private Map<String, String> users;


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
            Ticket ticket = new Ticket(ticketName.getText());
            tickets.put((++ticketCount), ticket);
            Button ticketButton = new Button();
            ticketButton.setText(ticketName.getText());
            ticketButton.setPrefSize(WIDTH_TICKET, HEIGHT_TICKET);
            ticketButton.setLayoutX(X_POSITION_TICKET);
            ticketButton.setLayoutY(initialHeight + ticketButton.getPrefHeight() + SPACE_BETWEEN_TICKETS);
            initialHeight = ticketButton.getLayoutY();
            projectScreen.getChildren().add(ticketButton);
            this.hideEditionMenu();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("El nombre del ticket es un campo obligatorio");
            alert.showAndWait();
        }
    }

    private void hideEditionMenu() {
        this.editionMenu.setStyle("-fx-background-color: green;");
        this.createTicketLabel.setVisible(false);
        this.ticketName.setVisible(false);
        this.ticketType.setVisible(false);
        this.ticketDescription.setVisible(false);
        this.addResponsableButton.setVisible(false);
        this.ticketCreatedButton.setVisible(false);
    }

    public void initData(String projectName, Map<String, String> users) {
        this.projectName.setText(projectName);
        this.users = users;
    }
}
