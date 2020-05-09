package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * javadoc bla bla bla
 * */
public class MenuPageController {

    @FXML
    private ComboBox itemBox;

    @FXML
    private ImageView itemImage;

    @FXML
    private Button proceedButton, exitButton;

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
        Parent root = FXMLLoader.load(getClass().getResource("idPage.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}

