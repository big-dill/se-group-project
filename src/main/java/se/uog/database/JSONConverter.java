package se.uog.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import se.uog.model.AppModel;

/**
 * Converts parts of our App (in this case, the AppModel) to and from JSON for
 * storage.
 */
public class JSONConverter {

    /**
     * Converts an AppModel to a JSON string.
     *
     * @param model the AppModel to convert to JSON
     * @return the JSON representation of the AppModel
     */
    public static String convertAppModelToJSON(AppModel model) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(AppModel.class, new AppModelSerializer())
            .create();

        return gson.toJson(model);
    }

    /**
     * Converts a JSON string to an AppModel.
     *
     * @param json the JSON string of an AppModel
     * @return an AppModel
     */
    public static AppModel convertJSONToAppModel(String json) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(AppModel.class, new AppModelDeserializer())
            .create();

        return gson.fromJson(json, AppModel.class);
    }

}
