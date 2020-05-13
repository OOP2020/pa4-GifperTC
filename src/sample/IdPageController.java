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
 * javadoc bla bla bla
 * */
public class IdPageController implements Initializable{
    boolean alreadyLogIn = false;
    private String itemName;
    private Stage window;
    private Parent root;
    private int queueNumber;

    @FXML
    private TextField textBox;

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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * javadoc bla bla bla
     * */
    public void setQueueButton (ActionEvent event) throws Exception {

            Items item = ItemFactory.getItem(itemName);
            Map<Integer, String> map = readDatabaseToMap(item);
            updateQueueNumber(map);

            String idNumber = textBox.getText();

            try {
                if (idNumber.isEmpty()) showDialog("Please enter your ID before running queue number");
                else if (!map.containsValue(idNumber)) {
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
                    alreadyLogIn = true;
                    window.showAndWait();
                }
                else if(alreadyLogIn || map.containsValue(idNumber)) {
                    showDialog("This ID has already used for this item.");
                }

                else {
                    root = FXMLLoader.load(getClass().getResource("idPage.fxml"));
                }
            } catch (NumberFormatException nfe) {
                textBox.setPromptText("Please enter number");
            } catch (NullPointerException npe) {
                textBox.setPromptText("Please enter some number");
            }
    }

    /**
     * javadoc bla bla bla
     * */
    public Map<Integer, String> readDatabaseToMap(Items item) {
        String fileName = item.getDatabase();
        Map<Integer, String> map = new HashMap<Integer, String>();
        File file = new File (fileName);

        if(!file.exists() || !file.isFile()) System.out.println("Where is the fucking file");
        if(!file.canRead()) System.out.println("I cant read fuck you");


        String line ;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){

            String[] inline;

            while((line= reader.readLine()) != null) {
                inline = line.trim().split(" ");

                if(inline.length == 2) {
                    int queueNumber = Integer.parseInt(inline[0].trim());
                    String idNumber = inline[1].trim();
                    map.put(queueNumber,idNumber);
                }
                
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return map;
    }

    public void updateQueueNumber (Map<Integer, String> map) {
        queueNumber += Integer.parseInt(String.valueOf(map.values().size()));
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

