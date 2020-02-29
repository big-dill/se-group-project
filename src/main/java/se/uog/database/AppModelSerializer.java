package se.uog.database;

import com.google.gson.*;
import se.uog.IDReferenced;
import se.uog.application.AppModel;
import se.uog.qualification.Qualification;
import se.uog.teacher.Teacher;
import se.uog.training.Training;

import java.lang.reflect.Type;

/**
 * A custom serializer for the AppModel wrapper.
 * <p>
 * This dictates how the model is stored as JSON and also will take care of ID
 * referencing classes which are referred to outside of their respective lists.
 */
public class AppModelSerializer implements JsonSerializer<AppModel> {

    public static final String QUALIFICATION_LIST_FIELD = "qualifications";
    public static final String TRAINING_LIST_FIELD = "training";
    public static final String TEACHER_LIST_FIELD = "teachers";
    public static final String COURSE_LIST_FIELD = "courses";

    private AppModel appModel;
    private JsonObject jsonObject = new JsonObject();

    @Override
    public JsonElement serialize(AppModel appModel, Type typeOfSrc, JsonSerializationContext context) {
        this.appModel = appModel;

        serializeQualifications();
        serializeTraining();
        serializeTeachers();
        serializeCourses();

        return jsonObject;
    }

    /**
     * This class is a JsonSerializer which can be 'hooked into' gson, so when it
     * hits anything with the class 'IDReferenced', it can store it just as its ID,
     * rather than the whole object..
     */
    private class IDReferencedSerializer implements JsonSerializer<IDReferenced> {

        @Override
        public JsonElement serialize(IDReferenced src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getID());
        }

    }

    /**
     * Serializes the qualifications into a json array
     */
    private void serializeQualifications() {
        Gson gson = new Gson();

        jsonObject.addProperty(QUALIFICATION_LIST_FIELD, gson.toJson(appModel.getQualificationArray()));
    }

    /**
     * Serializes the training into a json array.
     *
     * Converts the qualifications into just their ID references so that any POJO
     * references are preserved after deserialization.
     */
    private void serializeTraining() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Qualification.class, new IDReferencedSerializer())
            .create();

        jsonObject.addProperty(TRAINING_LIST_FIELD, gson.toJson(appModel.getTrainingArray()));
    }

    /**
     * Serializes the teachers into a json array.
     *
     * Converts the qualifications and the training into just their ID references so
     * that any POJO references are preserved after deserialization.
     */
    private void serializeTeachers() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Qualification.class, new IDReferencedSerializer())
            .registerTypeAdapter(Training.class, new IDReferencedSerializer())
            .create();

        jsonObject.addProperty(TEACHER_LIST_FIELD, gson.toJson(appModel.getTeacherArray()));
    }

    /**
     * Serialises the courses into a json array.
     *
     * Converts the teachers into their ID references so that any POJO references
     * are preserved after deserialization.
     */
    private void serializeCourses() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Qualification.class, new IDReferencedSerializer())
            .registerTypeAdapter(Teacher.class, new IDReferencedSerializer())
            .create();

        jsonObject.addProperty(COURSE_LIST_FIELD, gson.toJson(appModel.getCourseArray()));
    }

}
