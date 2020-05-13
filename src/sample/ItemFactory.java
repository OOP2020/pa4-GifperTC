package sample;

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
