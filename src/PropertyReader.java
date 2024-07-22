import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class PropertyReader {
    public static void main(String[] args) throws IOException {
        // first off, use the properties class to load the properties from RandomPropertyFiles folder
        Properties properties = new Properties();

        String propertyFilePath = "C:/Users/USER/IdeaProjects/RandomPropertyFiles/src/config3.properties";

        try (FileInputStream input = new FileInputStream(propertyFilePath)){
            // to load the property file and make changes to it
            properties.load(input);
        }

        // to make changes to the properties
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the key you want to update its value: ");
        String key = scanner.nextLine();

        System.out.print("Enter the new value for this key: ");
        String newValue = scanner.nextLine();

        properties.setProperty(key, newValue);

        // save the changes you have made
        try (FileOutputStream output = new FileOutputStream(propertyFilePath)) {
            properties.store(output, null);
        }
    }
}
