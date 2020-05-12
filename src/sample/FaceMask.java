package sample;

import java.io.*;

public class FaceMask implements Items {
    String dataBase = "text/FaceMask";

    @Override
    public File getDatabase() throws FileNotFoundException {
        return new File(dataBase);
    }

    @Override
    public void writeDatabase(int queueNumber, String idNumber) throws IOException {
        String output = String.format("%d %s\n",queueNumber,idNumber);
        FileWriter writer = new FileWriter(dataBase);
        writer.write(output);
    }
}
