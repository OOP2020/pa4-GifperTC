package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Tets {

    private static int queueNumber;

    public static void main(String[] args) {
        Map<Integer, String> map = readDatabaseToMap("src\\sample\\txt\\Alcohol.txt");
        display(map);
        updateQueueNumber (map);
        System.out.println(getQueueNumber());
//        System.out.println(map.keySet());
//        System.out.println(map.values());
    }

    public static int getQueueNumber() {
        return queueNumber;
    }

    /**
     * javadoc bla bla bla
     * */
    public static Map<Integer, String> readDatabaseToMap(String fileName) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        File file = new File (fileName);

        if(!file.exists() || !file.isFile()) System.out.println("Where is the fucking file");
        if(!file.canRead()) System.out.println("I cant read fuck you");


        String line ;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){

            String[] inline;

            while((line= reader.readLine()) != null) {
                inline = line.trim().split(" ");

                int queueNumber = Integer.parseInt(inline[0].trim());
                String idNumber = inline[1].trim();

                if(!map.containsValue(idNumber)) {
                    map.put(queueNumber,idNumber);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return map;
    }

    public static void updateQueueNumber(Map<Integer, String> map) {
        queueNumber += Integer.parseInt(String.valueOf(map.values().size()));
    }

    public static void display(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
