package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Enum class of give aways items that stores database file path of each items.
 * */
public enum Items{
    Facemask("src\\sample\\txt\\FaceMask.txt"),
    Alcohol("src\\sample\\txt\\Alcohol.txt"),
    Napkins("src\\sample\\txt\\Napkins.txt");

    private final String dataBase;

    /** enum constructor **/
    private Items(String dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Return database file path of that item.
     * */
    public String getDatabase(){
        return this.dataBase;
    }

    /**
     * Update database file path of that item with input id number from user and queue number.
     * */
    public void writeDatabase(int queueNumber, String idNumber) throws IOException {
        File file = new File(this.getDatabase());
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        String output = queueNumber + " " + idNumber + "\n";
        writer.write(output);
        writer.flush();
        writer.close();
    }
}
