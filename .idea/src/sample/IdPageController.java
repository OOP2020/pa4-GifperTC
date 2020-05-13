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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Controller class of all components in idPage.fxml file.
 * */
public class IdPageController implements Initializable{
    boolean alreadyLogIn = false;
    private String itemName;
    private Stage window;
    private Parent root;
    private int queueNumber = 0;

    @FXML
    private TextField textBox;

    @FXML
    private Button backButton;

    /**
     * Set action for clear button.
     * */
    public void setClearButton (ActionEvent event) {
        textBox.clear();
    }

    /**
     * Set action for back button.
     * */
    public void setBackButton (ActionEvent event) throws IOException {
        window = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("menuPage.fxml"));
        Scene scene = new Scene(root);
        window.setTitle("KU COVID-19 Giveaway queue runner");
        window.setScene(scene);
        window.show();
    }

    /**
     * Set itemname for field itemname.
     * */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Update queue number from values in map.
     * */
    public void updateQueueNumber (Map<Integer, String> map) {
        queueNumber = Integer.parseInt(String.valueOf(map.values().size()));
    }

    /**
     * Check if inputted id number from user is a numeric.
     * */
    public boolean isNumber(String idNum) {
        if (idNum == null) {
            return false;
        }
        try{
            Long num = Long.getLong(idNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Set action for clear button and detect multiple queue card running from same id.
     * */
    public void setQueueButton (ActionEvent event) throws Exception {
            Items item = ItemFactory.getItem(itemName);
            Map<Integer, String> map = readDatabaseToMap(item);
            updateQueueNumber(map);

            String idNumber = textBox.getText();

            if (idNumber.length() < 10) { showDialog("Please enter KU 10 digits Student ID"); }
            else if(!isNumber(idNumber)) { showDialog("Please enter number."); }
            else {
                try {
                    if (!map.containsValue(idNumber)) {
                        queueNumber+=1;
                        System.out.println(queueNumber);
                        item.writeDatabase(queueNumber,idNumber);

                        window = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("queueCard.fxml"));
                        root = (Parent) loader.load();
                        QueueCardController queueCardController = loader.getController();
                        queueCardController.setLabel(idNumber, itemName, queueNumber);
                        Scene scene = new Scene(root);
                        window.setScene(scene);
                        window.setResizable(false);
                        window.show();
                        alreadyLogIn = true;
                    }
                    else if (alreadyLogIn || map.containsValue(idNumber)) { showDialog("This ID has already used for this item."); }
                } catch (NullPointerException npe) {
                    textBox.setPromptText("Please enter something");
                }
            }
    }

    /**
     * Read from text database and stores queue number and id number in Hashmap.
     * */
    public Map<Integer, String> readDatabaseToMap(Items item) {
        String fileName = item.getDatabase();
        Map<Integer, String> map = new HashMap<Integer, String>();
        File file = new File (fileName);

        if (!file.exists() || !file.isFile()) System.out.println("Where is the fucking file");
        if (!file.canRead()) System.out.println("I cant read fuck you");

        String line ;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

            String[] inline;

            while ((line= reader.readLine()) != null) {
                inline = line.trim().split(" ");

                if (inline.length == 2) {
                    int queueNumber = Integer.parseInt(inline[0].trim());
                    String idNumber = inline[1].trim();
                    map.put(queueNumber, idNumber);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return map;
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

