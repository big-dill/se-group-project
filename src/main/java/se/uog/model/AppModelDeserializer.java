package se.uog.model;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class AppModelDeserializer implements JsonDeserializer<AppModel> {

    public AppModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        AppModel appModel = new AppModel();

        // No custom serializer needed here as these are 'primitive'
        // Parse qualifications
        Gson gson = new Gson();
        JsonElement qualificationArrayJson = jsonObject.get(AppModelSerializer.QUALIFICATION_LIST_FIELD);
        Qualification[] qualificationArray = gson.fromJson(qualificationArrayJson.getAsString(), Qualification[].class);
        appModel.setQualificationList(qualificationArray);

        // Custom deserializer for ID referenced Qualifications.
        // Parse teachers
        gson = new GsonBuilder()
                .registerTypeAdapter(Qualification.class, new IDReferencedDeserializer(qualificationArray)).create();
        JsonElement teacherArrayJson = jsonObject.get(AppModelSerializer.TEACHER_LIST_FIELD);
        Teacher[] teacherArray = gson.fromJson(teacherArrayJson.getAsString(), Teacher[].class);
        appModel.setTeacherList(teacherArray);

        gson = new GsonBuilder()
                .registerTypeAdapter(Qualification.class, new IDReferencedDeserializer(qualificationArray))
                .registerTypeAdapter(Teacher.class, new IDReferencedDeserializer(teacherArray)).create();
        JsonElement courseArrayJson = jsonObject.get(AppModelSerializer.COURSE_LIST_FIELD);

        Course[] courseArray = gson.fromJson(courseArrayJson.getAsString(), Course[].class);
        appModel.setCourseList(courseArray);

        System.out.println(qualificationArray);

        return appModel;
    }

    private class IDReferencedDeserializer implements JsonDeserializer<IDReferenced> {

        private IDReferenced[] referenceArray;

        public IDReferencedDeserializer(IDReferenced[] referenceArray) {
            this.referenceArray = referenceArray;
        }

        @Override
        public IDReferenced deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            String id = json.getAsString();

            for (IDReferenced item : referenceArray) {
                if (id.equals(item.getID())) {
                    return item;
                }
            }
            return null;
        }

    }

}
