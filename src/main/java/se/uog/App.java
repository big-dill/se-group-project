package se.uog;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import se.uog.controller.AppController;
import se.uog.model.AppModel;
import se.uog.model.AppModelDeserializer;
import se.uog.logger.Logger;

public class App {

    private static String LOGGER_FLAG = "-t";

    public static void main(String[] args) {

        AppModel appModel;

        Gson gson = new GsonBuilder().registerTypeAdapter(AppModel.class, new AppModelDeserializer()).create();
        try {
            appModel = gson.fromJson(new FileReader("model.json"), AppModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            appModel = new AppModel();
        }

        if (args.length > 0 && args[0].equals(LOGGER_FLAG)) {
            System.out.println("Logger enabled");
            Logger.getInstance().enable();
            Logger.getInstance().logToFileIfEnabled("Logger enabled.");
        }

        new AppController(appModel);
    }
}
