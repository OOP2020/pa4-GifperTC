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
 * Controller class of all components in menuPage.fxml file.
 * */
public class MenuPageController implements Initializable {

    @FXML
    private ComboBox<String> itemBox;

    @FXML
    private Button proceedButton;

    /**
     * Set action for exit button.
     * */
    public void setExitButton (ActionEvent event) {
        System.exit(1);
    }

    /**
     * Set action for proceed button.
     * */
    public void setProceedButton(ActionEvent event) throws Exception {
        if(itemBox.getValue() == null) {
            showDialog("Please select item before proceed");
        }
        else{
            Stage window = (Stage) proceedButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("idPage.fxml"));
            Parent root = (Parent) loader.load();
            IdPageController idPageController = loader.getController();
            idPageController.setItemName(itemBox.getValue());

            Scene scene = new Scene(root);
            window.setTitle(itemBox.getValue());
            window.setScene(scene);
            window.show();
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
    }
}

