package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.UserLogin;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label validationMessage;
    private UserLogin login;

    @FXML
    public void initialize() {
        this.login = new UserLogin();
    }

    public void login(ActionEvent event) throws IOException {

        Boolean userExists = this.login.validateUser(username.getText(), password.getText());


        if(userExists) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/projectManagement.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            appStage.setScene(scene);
            ProjectController controller = loader.<ProjectController>getController();
            controller.initData(login.getUsers());
            appStage.show();

        } else {
            validationMessage.setText("Nombre de usuario y/o contraseña incorrectos");
        }
    }
}
