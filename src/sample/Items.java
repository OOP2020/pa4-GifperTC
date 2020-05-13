package sample;

import java.io.BufferedWriter;
import java.io.File;
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
        File file = new File(this.getDatabase());
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        String output = queueNumber + " " + idNumber + "\n";
        writer.write(output);
        writer.flush();
        writer.close();
    }
}
