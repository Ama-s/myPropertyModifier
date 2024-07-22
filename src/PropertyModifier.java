import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class PropertyModifier {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        FileOutputStream outputFile = null;

        try {
            FileReader propertyFileModifier = new FileReader("C:\\Users\\USER\\IdeaProjects\\RandomPropertyFiles\\src\\makeChanges.txt");
            BufferedReader bufferedPropertyModifier = new BufferedReader(propertyFileModifier);
            Scanner myChanges = new Scanner(bufferedPropertyModifier);

            while (myChanges.hasNextLine()) {
                String line = myChanges.nextLine();
                // checks whether there is another line in the input

                if (line.startsWith("[") && line.endsWith("]")) {
                    // [] indicates the start of a filepath, it extracts the filepath

                    if (outputFile != null) {
                        //  checks whether the outputFile object has been assigned a value

                        // if yes it means an outputFile already exists, save it and close the existing file
                        properties.store(outputFile, null);
                        outputFile.close();

                        // initialize a new properties class for the new file
                        properties = new Properties();
                    }
                    line = line.replace("[", "").replace("]", "");

                    // use the properties class to load the properties from RandomPropertyFiles folder
                    properties.load(new FileInputStream(line));
                    outputFile = new FileOutputStream(line);

                } else if (line.contains("=")){
                    String[] keyValuePair = line.split("=");
                    if (keyValuePair.length == 2) {
                        // Split the line into key-value pair

                        String key = keyValuePair[0].trim();
                        String newValue = keyValuePair[1].trim();

                        // update the new value
                        properties.setProperty(key, newValue);
                        }
                    }
                }
            } finally {
                if (outputFile != null) {
                    // save the last updated file and close
                    properties.store(outputFile, null);
                    outputFile.close();
                }
            }
        }

}
