package views;

import javafx.scene.control.Label;

public class LoginView {

    private Label validationMessage;


    public LoginView(Label validationMessage) {
        this.validationMessage = validationMessage;
    }


    public void setValidationMessage(String validationMessage) {
        this.validationMessage.setText(validationMessage);
    }
}
