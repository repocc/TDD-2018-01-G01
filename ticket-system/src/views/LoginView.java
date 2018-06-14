package views;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginView {

    private TextField username;
    private PasswordField password;
    private Label validationMessage;


    public LoginView(TextField username, PasswordField password, Label validationMessage) {
        this.username = username;
        this.password = password;
        this.validationMessage = validationMessage;
    }

    public LoginView() {

    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public Label getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage.setText(validationMessage);
    }
}
