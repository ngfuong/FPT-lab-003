package lab003;

import java.util.Scanner;
import java.io.*;

public class FileIO {
    String name;


    public final String getFilePath(String input) {
        return System.getProperty("user.dir") + input;
    }

    public File readData(String path, String type) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.createNewFile();
                return f;
            }

            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                if (type.equals("c")) {
                    String line = reader.nextLine();
                    String delim = "[{}:',]+";

                    String id = line.split(delim)[2];
                    String name = line.split(delim)[4];

                    int pos = this.search(username);

                }
            }


            return f;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeData() {

    }

}
