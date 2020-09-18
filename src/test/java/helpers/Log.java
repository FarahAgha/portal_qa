package helpers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log {

    public Log(){
        // Set up a simple configuration that logs on the console.
        BasicConfigurator.configure();
    }

    private static Logger Log = Logger.getLogger(Log.class.getName());


    public static void startTestCase(String sTestCaseName){
        Log.info("Started Test case");
    }

    public static void endTestCase(String sTestCaseName){
        Log.info("Ended Test Case");
    }

    public static void info(String message)
    {
        Log.info(message);
    }
}