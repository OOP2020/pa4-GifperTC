package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * javadoc bla bla bla
 * */
public class IdPageController implements Initializable{
    private Stage window;
    private Parent root;
    private boolean alreadyLogIn = false;

    @FXML
    private TextField textBox;

    @FXML
    private Label itemName;

    @FXML
    private Button proceedButton, exitButton, clearButton, queueButtton, backButton;

    /**
     * javadoc bla bla bla
     * */
    public void setClearButton (ActionEvent event) {
        textBox.clear();
    }

    /**
     * javadoc bla bla bla
     * */
    public void setBackButton (ActionEvent event) throws IOException {
        window = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("menuPage.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    /**
     * javadoc bla bla bla
     * */
    public void setQueueButton (ActionEvent event) throws Exception {
            String text = textBox.getText();

            try {
                if (text.isEmpty()) showDialog("Please enter your ID before running queue number");
                else if(alreadyLogIn) {
                    showDialog("This ID has already used for this item.");
                }
                else {
                    window = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("queueCard.fxml"));
                    root = (Parent) fxmlLoader.load();
                    Scene scene = new Scene(root);
                    window.setScene(scene);
                    window.show();
                    alreadyLogIn = true;
                }
                root = FXMLLoader.load(getClass().getResource("idPage.fxml"));
            } catch (NumberFormatException nfe) {
                textBox.setPromptText("Please enter number");
            } catch (NullPointerException npe) {
                textBox.setPromptText("Please enter goddamn number");
            }
    }

    /**
     * Show alert dialog over the application box.
     * */
    public void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        itemBox.getItems().addAll();
    }
}

