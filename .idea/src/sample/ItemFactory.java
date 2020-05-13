package sample;

/**
 * Factory class for getting database file path of each items.
 * */
public class ItemFactory {
    public static Items getItem(String itemName){
        switch (itemName) {
            case "Facemask":
                return Items.Facemask;
            case "Alcohol":
                return Items.Alcohol;
            case "Napkins":
                return Items.Napkins;
            default:
                return Items.Facemask;
        }
    }
}
