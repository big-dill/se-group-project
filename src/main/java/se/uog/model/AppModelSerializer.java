package se.uog.model;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class AppModelSerializer implements JsonSerializer<AppModel> {

    public static final String QUALIFICATION_LIST_FIELD = "qualifications";
    public static final String TEACHER_LIST_FIELD = "teachers";
    public static final String COURSE_LIST_FIELD = "courses";

    @Override
    public JsonElement serialize(AppModel appModel, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Store qualifications
        Gson gson = new Gson();
        jsonObject.addProperty(QUALIFICATION_LIST_FIELD, gson.toJson(appModel.getQualificationArray()));

        // For the teacher's we'll need to convert the qualifications to just their IDs.
        gson = new GsonBuilder().registerTypeAdapter(Qualification.class, new IDReferencedSerializer()).create();

        // Store teachers
        jsonObject.addProperty(TEACHER_LIST_FIELD, gson.toJson(appModel.getTeacherArray()));

        // Store courses
        gson = new GsonBuilder().registerTypeAdapter(Qualification.class, new IDReferencedSerializer())
                .registerTypeAdapter(Teacher.class, new IDReferencedSerializer()).create();

        jsonObject.addProperty(COURSE_LIST_FIELD, gson.toJson(appModel.getCourseArray()));

        System.out.println(jsonObject.toString());

        return jsonObject;
    }

    private class IDReferencedSerializer implements JsonSerializer<IDReferenced> {

        @Override
        public JsonElement serialize(IDReferenced src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getID());
        }

    }

}
