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
public class MenuPageController implements Initializable {

    @FXML
    private ComboBox<String> itemBox;

    @FXML
    private Button proceedButton;

    /**
     * javadoc bla bla bla
     * */
    public void setExitButton (ActionEvent event) {
        System.exit(1);
    }

    /**
     * javadoc bla bla bla
     * */
    public void setProceedButton(ActionEvent event) throws Exception {
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

    /**
     * javadoc bla bla bla
     *
     * */
    public Items getItemName() {
        return ItemFactory.getItem(itemBox.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}

