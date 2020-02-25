package se.uog;

import se.uog.controller.AppController;
import se.uog.model.AppModel;
import se.uog.logger.Logger;


public class App {

    private static String LOGGER_FLAG = "-t";

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals(LOGGER_FLAG)) {
                System.out.println("Logger enabled");
                Logger.getInstance().enable();
                Logger.getInstance().logToFileIfEnabled("Logger enabled.");
            }

        AppModel appModel = new AppModel();
        new AppController(appModel);
        new AppController(appModel);
    }
}
