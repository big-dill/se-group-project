package se.uog.database;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import se.uog.model.AppModel;
import se.uog.model.Course;
import se.uog.model.IDReferenced;
import se.uog.model.Qualification;
import se.uog.model.Teacher;
import se.uog.model.Training;

/**
 * A custom deserializer for the AppModel wrapper.
 *
 * This reconstructs the AppModel from JSON by creating the lists in order and
 * resolving any ID references to the original objects.
 */
public class AppModelDeserializer implements JsonDeserializer<AppModel> {

    JsonObject sourceJsonObject;
    AppModel appModel = new AppModel(); // The target AppModel which will be returned.

    public AppModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        sourceJsonObject = json.getAsJsonObject();

        // Order is extremely important here!
        // Lists containing objects which are referenced by other objects have to be
        // deserialized first.
        Qualification[] qualificationArray = deserializeQualifications();
        appModel.setQualificationList(qualificationArray);

        Training[] trainingArray = deserializeTraining(qualificationArray);
        appModel.setTrainingList(trainingArray);

        Teacher[] teacherArray = deserializeTeachers(qualificationArray, trainingArray);
        appModel.setTeacherList(teacherArray);

        Course[] courseArray = deserializeCourses(qualificationArray, teacherArray);
        appModel.setCourseList(courseArray);

        return appModel;
    }

    // The following set the custom ID deserializers for the appropriate fields and
    // allow array lookups using these ids by passing the appropriate array to the
    // method

    private Qualification[] deserializeQualifications() {
        Gson gson = new Gson();
        JsonElement qualificationArrayJson = sourceJsonObject.get(AppModelSerializer.QUALIFICATION_LIST_FIELD);
        return gson.fromJson(qualificationArrayJson.getAsString(), Qualification[].class);
    }

    private Training[] deserializeTraining(Qualification[] qualificationArray) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Qualification.class, new IDReferencedDeserializer(qualificationArray))
            .create();

        JsonElement trainingArrayJson = sourceJsonObject.get(AppModelSerializer.TRAINING_LIST_FIELD);
        return gson.fromJson(trainingArrayJson.getAsString(), Training[].class);
    }

    private Teacher[] deserializeTeachers(Qualification[] qualificationArray, Training[] trainingArray) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Qualification.class, new IDReferencedDeserializer(qualificationArray))
            .registerTypeAdapter(Training.class, new IDReferencedDeserializer(trainingArray))
            .create();

        JsonElement teacherArray = sourceJsonObject.get(AppModelSerializer.TEACHER_LIST_FIELD);
        return gson.fromJson(teacherArray.getAsString(), Teacher[].class);
    }

    private Course[] deserializeCourses(Qualification[] qualificationArray, Teacher[] teacherArray) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Qualification.class, new IDReferencedDeserializer(qualificationArray))
            .registerTypeAdapter(Teacher.class, new IDReferencedDeserializer(teacherArray))
            .create();

        JsonElement courseArrayJson = sourceJsonObject.get(AppModelSerializer.COURSE_LIST_FIELD);
        return gson.fromJson(courseArrayJson.getAsString(), Course[].class);
    }

    /**
     * This class deserializes any IDReferenced items.
     */
    private class IDReferencedDeserializer implements JsonDeserializer<IDReferenced> {

        private IDReferenced[] referenceArray;

        /**
         * Creates a new IDReferencedDeserializer. It will extract the ID from the
         * IDReferenced class and will attempt to look it up in the referenceArray
         * passed to the constructor.
         *
         * If the item is found, it is returned. Otherwise, if the item with the ID is
         * not found in the array, the object is returned as null.
         *
         * @param referenceArray
         */
        public IDReferencedDeserializer(IDReferenced[] referenceArray) {
            this.referenceArray = referenceArray;
        }

        @Override
        public IDReferenced deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            // The JSON should always be an ID.
            String id = json.getAsString();

            // Search the referenceArray and return the item if it exists.
            // TODO: This could be sped up if the array is stored in ID ORDER.
            for (IDReferenced item : referenceArray) {
                if (id.equals(item.getID())) {
                    return item;
                }
            }
            return null;
        }
    }
}
