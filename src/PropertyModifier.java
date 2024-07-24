import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class PropertyModifier {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        FileOutputStream outputFile = null;

        try (Scanner myChanges = new Scanner(new BufferedReader(new FileReader("C:\\Users\\USER\\IdeaProjects\\RandomPropertyFiles\\src\\makeChanges.txt")))){
            while (myChanges.hasNextLine()) {
                String line = myChanges.nextLine(); // checks whether there is another line in the input

                if (line.startsWith("[") && line.endsWith("]")) { // [] indicates the start of a filepath, it extracts the filepath

                    if (outputFile != null) { // checks whether the outputFile object has been assigned a value

                        // Manually write properties to the output file exactly as they are
                        writeProperties(properties, outputFile);
                        outputFile.close();
                    }
                    line = line.replace("[", "").replace("]", "");

                    //clear properties for the new file, ensuring that no data from previous files remains in memory
                    properties.clear();

                    // use the properties object to load the properties from RandomPropertyFiles folder
                    properties.load(new FileInputStream(line));
                    outputFile = new FileOutputStream(line);

                } else if (line.contains("=")){
                    String[] keyValuePair = line.split("=", 2);
                    String key = keyValuePair[0].trim();
                    String newValue = keyValuePair[1].trim();

                    // update the new value
                    properties.setProperty(key, newValue);

                    }
                }
            } finally {
                if (outputFile != null) {
                    // save the last updated file and close
                    writeProperties(properties, outputFile);
                    outputFile.close();
                }
            }
        }
    private static void writeProperties(Properties properties, FileOutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            writer.write(key + "=" + value);
            writer.newLine();
        }
        writer.flush();
    }

}
