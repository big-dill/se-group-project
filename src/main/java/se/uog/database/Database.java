package se.uog.database;

import com.google.gson.Gson;

import se.uog.model.AppModel;

public class Database {
    public Database(AppModel model) {
        // Serialise model into JSON
        Gson gson = new Gson();

        String serialisedModel = gson.toJson(model);

        System.out.println(serialisedModel);

    }
}
