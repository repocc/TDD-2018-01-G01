package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Project;
import model.Ticket;
import views.AlertView;
import views.TicketView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TicketController {


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

    private TicketView view;
    private Project project;
    private Ticket actualTicket;
    private Button actualButton;

    public void initData(Project project) {
        this.view = TicketView.getInstance();
        this.projectName.setText(project.getProjectName());
        this.project = project;
        this.setViewValues();
        this.view.showStates(project.getStates());
    }

    public void setViewValues() {
        this.view.setProjectScreen(projectScreen);
        this.view.setEditionMenu(editionMenu);
        this.view.setProjectName(projectName);
        this.view.setTicketName(ticketName);
        this.view.setTicketDescription(ticketDescription);
        this.view.setTicketType(ticketType);
        this.view.setCreateTicketLabel(createTicketLabel);
        this.view.setAddResponsableButton(addResponsableButton);
        this.view.setCreateTicketButton(createTicketButton);
        this.view.setTicketCreatedButton(ticketCreatedButton);
        this.view.setSaveChangesButton(saveChangesButton);
    }

    public void createTicket() {
        this.actualTicket = new Ticket(ticketName.getText(), ticketDescription.getText(), ticketType.getText());
        this.view.setVisibility("-fx-background-color: white;", true, ticketCreatedButton);
        this.view.setTexts();

    }

    public void addTicket() {
        if (!ticketName.getText().equals("")) {
            actualTicket.setTitle(ticketName.getText());
            actualTicket.setDescription(ticketDescription.getText());
            actualTicket.setType(ticketType.getText());
            actualTicket.setActualState(project.getStates().get(0));
            project.addTicket(actualTicket);
            final Button ticketButton = new Button();
            final Ticket ticket = actualTicket;
            this.view.addTicket(ticketButton);
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
            AlertView.createAlert("El nombre del ticket es un campo obligatorio");
        }
    }

    public void openResponsableScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("responsable.fxml")));
        Scene scene = null;
        try {
            scene = new Scene((Parent) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage appStage = new Stage();
        appStage.hide();
        appStage.setScene(scene);
        ResponsableController controller = loader.<ResponsableController>getController();
        controller.initData(project.getRolesAssignment());
        appStage.show();

    }


    private void createContextMenu(MouseEvent event, final Ticket ticket, final Button button) {

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
               changeState(ticket, button);
            }
        });

        menu.getItems().addAll(item1, item2);
        menu.show(button, event.getScreenX(), event.getScreenY());

    }

    private void changeState(final Ticket ticket, final Button buttonTicket) {
        final AnchorPane pane = new AnchorPane();
        final Button button = new Button();
        this.view.setValues(pane, button);
        this.showStatesToGo(ticket.getActualState(), pane);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ticket.setActualState(view.getStateClicked());
                view.disappearStatePane(pane, button);
                view.moveTicket(buttonTicket);
            }
        });
        projectScreen.getChildren().addAll(pane, button);
    }

    private void showStatesToGo(String actualState, Pane pane) {
        if (project.getStateFlow().containsKey(actualState)) {
            ArrayList<Object> possibleStates = project.getStateFlow().get(actualState);
            this.view.showStatesToGo(pane, possibleStates);
        }
    }

    private void addCommentScreen(final Ticket ticket) {
        final TextArea textArea = new TextArea();
        final Button button = new Button();
        this.view.showCommentScreen(textArea, button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ticket.addComment(textArea.getText());
                view.disappearCommentScreen(textArea, button);
            }
        });
        projectScreen.getChildren().addAll(textArea, button);

    }

    private void editTicket(Ticket ticket, Button button) {
        this.actualTicket = ticket;
        this.view.setVisibility("-fx-background-color: white;", true, saveChangesButton);
        this.setFields(ticket);
        actualButton = button;
    }

    private void setFields(Ticket ticket) {
        this.ticketName.setText(ticket.getTitle());
        this.ticketDescription.setText(ticket.getDescription());
        this.ticketType.setText(ticket.getType());
    }

    private void hideEditionMenu(Button button) {
        this.view.setVisibility("-fx-background-color: green;", false, button);
    }


    public void saveChanges() {
        if (!ticketName.getText().equals("")) {
            this.actualTicket.setTitle(ticketName.getText());
            this.actualTicket.setDescription(ticketDescription.getText());
            this.actualTicket.setType(ticketType.getText());
            this.actualButton.setText(ticketName.getText());
            this.hideEditionMenu(saveChangesButton);
        } else {
            AlertView.createAlert("El nombre del ticket es un campo obligatorio");
        }
    }

}
