package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * javadoc bla bla bla
 * */
public class IdPageController{
    private Stage window;
    private Parent root;
    private boolean alreadyLogIn = false;

    @FXML
    private TextField textBox;

    @FXML
    private Label itemName;

    @FXML
    private Button clearButton, queueButton, backButton;

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
            Map<Integer, String> map = readDatabaseToMap();

            try {
                if (text.isEmpty()) showDialog("Please enter your ID before running queue number");
                else if(alreadyLogIn || map.containsValue(text)) {
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
     * javadoc bla bla bla
     * */
    private static Map<Integer, String> readDatabaseToMap(String fileName) {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        File file = new File (fileName);

        if (!file.exists() || !file.isFile())
            showDialog(fileName + " does not exist or is not a regular file.");
        if (!file.canRead())
            showDialog(fileName + " is not readable");

        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String[] inline;
            while((line = reader.readLine()) != null) {
                inline = line.trim().split(" ");

                int queueNumber = Integer.parseInt(inline[0].trim());
                String idNumber = inline[1].trim();
                map.put(queueNumber,idNumber);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return map;
    }

//    /**
//     * javadoc bla bla bla
//     * */
//    private static void writeDatabase(Map map) throws Exception{
//        FileWriter writer = new FileWriter(filename);
//        BufferedWriter buffer = new BufferedWriter(writer);
//        buffer.write();
//    }

    /**
     * javadoc bla bla bla
     * */
    private boolean checkFile(String string) {
//        if(string.)

//        if (!file.exists() || !file.isFile())
//            showDialog(fileName + " does not exist or is not a regular file.");
//        if (!file.canRead())
//            showDialog(fileName + " is not readable");
    }

    /**
     * Show alert dialog over the application box.
     * */
    public static void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * javadoc bla bla bla
     * */
    public void initialize() {
//        itemBox.getItems().addAll();
    }
}

