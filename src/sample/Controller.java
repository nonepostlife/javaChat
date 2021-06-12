package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea chatArea;
    @FXML
    TextArea textMessageArea;
    @FXML
    Button sendButton;

    public void sendMessageFromButton(ActionEvent actionEvent) {
        sendMessage();
    }

    public void sendMessage() {
        if (textMessageArea.getText().isEmpty()) {
            return;
        }
        String text = this.textMessageArea.getText();
        String message = String.format("User [%s]\n%s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")), text);
        chatArea.appendText(message);
        textMessageArea.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void textAreaPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            keyEvent.consume();
            if (keyEvent.isShiftDown()) {
                textMessageArea.appendText(System.getProperty("line.separator"));
            } else {
                if(!textMessageArea.getText().isBlank()){
                    sendMessage();
                }
            }
        }
    }
}
