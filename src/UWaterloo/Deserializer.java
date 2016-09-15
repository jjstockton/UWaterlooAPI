package UWaterloo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Deserializer {

    public Course course;


    public Deserializer(JSONObject json, Class c) {

        this.course = new Course();

        JSONObject data = json;

        //Iterator keys = data.keys();

        ArrayList<String> keys = new ArrayList<>();
        data.keys().forEachRemaining(keys::add);


        for(String key : keys){
            try {

                Object value = data.get(key);
                Class type = value.getClass();

                String camelCaseKey = JsonUtils.toCamelCase(key);

                String setterMethod = getSetterMethod(camelCaseKey);

                if(type == JSONArray.class){
                    value = JsonUtils.toStringArray((JSONArray) value);
                    type = String[].class;
                }


                if(type == JSONObject.class || type == JSONObject.NULL.getClass()){
                    System.out.println(key + " " + type);
                    continue;
                }

                Method setter = c.getDeclaredMethod(setterMethod, type);

                //System.out.println(data.get(key).getClass());


                setter.invoke(course, value);


            }catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }

        }




    }

    private String getSetterMethod(String field){
        return "set" + field.substring(0,1).toUpperCase() + field.substring(1);
    }


}
