package views;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class TicketView {

    private static final Double HEIGHT_TICKET = 110.0;
    private static final Double WIDTH_TICKET = 90.0;
    private static final Double X_POSITION_TICKET = 58.0;
    private static final Double INITIAL_HEIGHT = 115.0;
    private static final Double SPACE_BETWEEN_TICKETS = 10.0;
    private static final Double SPACE_BETWEEN_TICKETS_X = 60.0;
    private static final Double Y_POSITION_LABEL = 150.0;
    private static final Double VARIATION_Y = 15.0;

    private Label projectName;
    private AnchorPane projectScreen;
    private Pane editionMenu;
    private TextField ticketName;
    private TextArea ticketDescription;
    private TextField ticketType;
    private Label createTicketLabel;
    private Button addResponsableButton;
    private Button ticketCreatedButton;
    private Button createTicketButton;
    private Button saveChangesButton;

    private Double positionX = X_POSITION_TICKET;
    private Double initialHeight = INITIAL_HEIGHT;
    private List<Label> labelsStates;
    private String stateClicked;

    private static TicketView instance;

    public TicketView() {
        this.labelsStates = new ArrayList<>();
    }

    public static TicketView getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new TicketView();
            return instance;
        }
    }

    public void showStates(List<String> states) {
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


    public void setProjectName(Label projectName) {
        this.projectName = projectName;
    }


    public void setProjectScreen(AnchorPane projectScreen) {
        this.projectScreen = projectScreen;
    }


    public void setEditionMenu(Pane editionMenu) {
        this.editionMenu = editionMenu;
    }


    public void setTicketName(TextField ticketName) {
        this.ticketName = ticketName;
    }


    public void setTicketDescription(TextArea ticketDescription) {
        this.ticketDescription = ticketDescription;
    }


    public void setTicketType(TextField ticketType) {
        this.ticketType = ticketType;
    }


    public void setCreateTicketLabel(Label createTicketLabel) {
        this.createTicketLabel = createTicketLabel;
    }


    public void setAddResponsableButton(Button addResponsableButton) {
        this.addResponsableButton = addResponsableButton;
    }


    public void setTicketCreatedButton(Button ticketCreatedButton) {
        this.ticketCreatedButton = ticketCreatedButton;
    }


    public void setCreateTicketButton(Button createTicketButton) {
        this.createTicketButton = createTicketButton;
    }


    public void setSaveChangesButton(Button saveChangesButton) {
        this.saveChangesButton = saveChangesButton;
    }

    public void setVisibility(String style, boolean visibility, Button button) {
        this.editionMenu.setStyle(style);
        this.createTicketLabel.setVisible(visibility);
        this.ticketName.setVisible(visibility);
        this.ticketType.setVisible(visibility);
        this.ticketDescription.setVisible(visibility);
        this.addResponsableButton.setVisible(visibility);
        button.setVisible(visibility);
    }

    public void setTexts() {
        this.ticketName.setText("");
        this.ticketType.setText("");
        this.ticketDescription.setText("");
    }

    public void addTicket(Button ticketButton) {
        ticketButton.setText(ticketName.getText());
        ticketButton.setPrefSize(WIDTH_TICKET, HEIGHT_TICKET);
        ticketButton.setLayoutX(X_POSITION_TICKET);
        ticketButton.setLayoutY(initialHeight + ticketButton.getPrefHeight() + SPACE_BETWEEN_TICKETS);
        initialHeight = ticketButton.getLayoutY();
        projectScreen.getChildren().add(ticketButton);
    }

    public void setValues(AnchorPane pane, Button button) {
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        pane.setPrefSize(80.0, 150.0);
        pane.setStyle("-fx-background-color: white;");
        button.setLayoutX(0);
        button.setLayoutY(pane.getPrefHeight());
        button.setPrefSize(80.0, 25.0);
        button.setText("Cambiar estado");
    }

    public void disappearStatePane(Pane pane, Button button) {
        pane.setVisible(false);
        button.setVisible(false);
    }

    public void disappearCommentScreen(TextArea textArea, Button button) {
        textArea.setVisible(false);
        button.setVisible(false);
    }

    public void moveTicket(Button button) {
        for (Label label : labelsStates) {
            if (label.getText().equals(stateClicked)) {
                button.setLayoutX(label.getLayoutX());
            }
        }
    }

    public void showStatesToGo(Pane pane, List<String> possibleStates) {

        Double positionYStates = 0.0;
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
            positionYStates += VARIATION_Y;
            pane.getChildren().add(label);
        }
    }

    public void showCommentScreen(TextArea textArea, Button button) {

        textArea.setPromptText("Escriba su comentario...");
        textArea.setPrefSize(200.0, 60.0);
        textArea.setLayoutX(0);
        textArea.setLayoutY(0);
        button.setText("Comentar");
        button.setPrefSize(200.0, 30.0);
        button.setLayoutX(0);
        button.setLayoutY(textArea.getPrefHeight());
    }

    public String getStateClicked() {
        return stateClicked;
    }
}
