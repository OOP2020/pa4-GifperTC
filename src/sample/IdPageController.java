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
public class IdPageController implements Initializable{
    private String itemName;
    private Stage window;
    private Parent root;
    private boolean alreadyLogIn = false;
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

            String idNumber = textBox.getText();

            try {
                if (idNumber.isEmpty()) showDialog("Please enter your ID before running queue number");
                else if(alreadyLogIn || map.containsValue(idNumber)) {
                    showDialog("This ID has already used for this item.");
                }
                else if (!map.containsValue(idNumber)) {
                    alreadyLogIn = false;
                    queueNumber+=1;
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
    private Map<Integer, String> readDatabaseToMap(Items item) {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        String fileName = item.getDatabase();
        File file = new File (fileName);

        if(!file.exists() || !file.isFile()) System.out.println("Where is the fucking file");
        if(!file.canRead()) System.out.println("I cant read fuck you");

        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String[] inline;
            while((line = reader.readLine()) != null) {
                inline = line.trim().split(" ");

                int queueNumber = Integer.parseInt(inline[0].trim());
                String idNumber = inline[1].trim();

                if(!map.containsValue(idNumber)) {
                    map.put(queueNumber,idNumber);
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
    public static void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

