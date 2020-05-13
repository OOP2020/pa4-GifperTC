package sample;

import java.io.FileWriter;
import java.io.IOException;

public enum Items{
    Facemask("src\\sample\\FaceMask.txt"),
    Alcohol("src\\sample\\Alcohol.txt"),
    Napkins("src\\sample\\Napkins.txt");

    private final String dataBase;

    private Items(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getDatabase(){
        return this.dataBase;
    }

    public void writeDatabase(int queueNumber, String idNumber) throws IOException {
        String output = String.format("%d %s\n",queueNumber,idNumber);
        FileWriter writer = new FileWriter(this.getDatabase());
        writer.write(output);
    }
}
