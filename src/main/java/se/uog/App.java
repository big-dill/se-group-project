package se.uog;

import se.uog.controller.AppController;
import se.uog.logger.Logger;


public class App {

    private static String LOGGER_FLAG = "-t";

    public static void main(String[] args) {

        boolean writeGameLogsToFile = false; // Should we write game logs to file?
        if (args.length > 0) {
            if (args[0].equals(LOGGER_FLAG)) {
                System.out.println("Logger enabled");
                Logger.getInstance().enable();
                Logger.getInstance().logToFileIfEnabled("Logger enabled.");
            }
        }

        new AppController();
    }
}
