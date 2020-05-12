package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Items {
    public File getDatabase() throws FileNotFoundException;

    public void writeDatabase(int queueNumber, String idNumber) throws IOException;
}
