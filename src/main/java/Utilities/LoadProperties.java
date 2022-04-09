package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    public static LoadProperties instance;
    public static Properties prop;
    public static final Object lock = new Object();
    // Load the properties file from the folder
    public static Properties jiraProperty =
            loadProperties(System.getProperty("user.dir") +
                    File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Jira.properties");
    public static Properties zapProperty =
            loadProperties(System.getProperty("user.dir") +
                    File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "zap.properties");
    //    public static Properties qmetryProperty =
//            loadProperties(System.getProperty("user.dir") +
//                    File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "qmetry.properties");
    public static String propertyFilePath = System.getProperty("user.dir") +
            File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "qmetry.properties";

    public static Properties loadProperties(String path){
        Properties pro = new Properties();
        // stream for reading file
        try {
            FileInputStream stream = new FileInputStream(path);
            pro.load(stream);
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred :  " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error occurred :  " + e.getMessage());
        }
        catch (NullPointerException e) {
            System.out.println("Error occurred :  " + e.getMessage());
        }

        return pro;
    }
    //Create a Singleton instance. We need only one instance of Property Manager.
    public static LoadProperties getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new LoadProperties();
                loadData();
            }
        }
        return instance;
    }

    //Get all configuration data and assign to related fields.
    public static void loadData() {
        //Declare a properties object
        prop = new Properties();

        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
    }
}
