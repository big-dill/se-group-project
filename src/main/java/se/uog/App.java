package se.uog;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import se.uog.controller.AppController;
import se.uog.model.AppModel;
import se.uog.model.AppModelDeserializer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AppModel appModel;

        Gson gson = new GsonBuilder().registerTypeAdapter(AppModel.class, new AppModelDeserializer()).create();
        try {
            appModel = gson.fromJson(new FileReader("model.json"), AppModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            appModel = new AppModel();
        }

        new AppController(appModel);
    }

}
