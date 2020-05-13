package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QueueCardController {

    @FXML
    private Label idDisplay, itemDisplay, queueDisplay;

    public void setLabel(String id, String itemName, int queue) {
        idDisplay.setText(id);
        itemDisplay.setText(itemName);
        queueDisplay.setText(String.valueOf(queue));
    }

}
