package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Stage window;

    private Parent root;

    @FXML
    private TextField textBox;

    @FXML
    private ComboBox itemBox;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Button proceedButton, exitButton, clearButton, queueButtton, backButton;

    /**
     * javadoc bla bla bla
     * */
    public void exitBtnHandler (ActionEvent event) {
        System.exit(1);
    }

    /**
     * javadoc bla bla bla
     * */
    public void clearBtnHandler(ActionEvent event) {
        textBox.clear();
    }

    /**
     * javadoc bla bla bla
     * */
    public void SceneBtnHandler(ActionEvent event) throws Exception {
        if (event.getSource() == proceedButton) {
            window = (Stage) proceedButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("idPage.fxml"));
        }

        else if (event.getSource() == queueButtton) {
            String text = textBox.getText();

            try {
                if (text.isEmpty()) showDialog("Please enter your ID before running queue number");

            } catch (NumberFormatException nfe) {
                textBox.setPromptText("Please enter number");
            } catch (NullPointerException npe) {
                textBox.setPromptText("Please enter goddamn number");
                //condition if ID is used before
                //if it is -> pop up error
//            showDialog("This ID has already used for this item.");

                //if not
                window = (Stage) queueButtton.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("queueCard.fxml"));
                showDialog("Go to queue card display");
            }
        }

        else{
            window = (Stage) backButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("menuPage.fxml"));
            }

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
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
