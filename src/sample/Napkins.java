package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Napkins implements Items{
    String dataBase = "text/Napkins";

    @Override
    public File getDatabase() throws FileNotFoundException {
        return new File(this.dataBase);
    }

    @Override
    public void writeDatabase(int queueNumber, String idNumber) throws IOException {
        String output = String.format("%d %s\n",queueNumber,idNumber);
        FileWriter writer = new FileWriter(dataBase);
        writer.write(output);
    }
}
