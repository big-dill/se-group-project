package se.uog.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import se.uog.model.AppModel;
import se.uog.model.AppModelSerializer;

public class Database {
    public Database(AppModel model) {

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(AppModel.class, new AppModelSerializer())
            .create();

        String json = gson.toJson(model);

        System.out.println(json);

    }
}
