package sample;

import java.io.File;
import java.io.FileNotFoundException;

public class ItemFactory {
    public static ItemName[] getItemName() {
        return ItemName.values();
    }

    public static File getDatabase(String itemName) throws FileNotFoundException {
        File database;
        switch (itemName) {
            case "Facemask":
                return FaceMask.getDatabase();
            case "Alcohol":
                return Alcohol.getDatabase();
            case "Weight":
                return FaceMask.getDatabase();
            default:
                return new Items()[0];
        }
    }
}

