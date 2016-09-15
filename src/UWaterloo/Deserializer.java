package UWaterloo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

class Deserializer {

    private  Class c;


     Deserializer(Class c) {
        this.c = c;
    }


     Object deserialize(JSONObject json) {

        Object newObject = null;

        try {

            newObject = c.newInstance();

            Iterator keys = json.keys();

            while(keys.hasNext()){

                String key = (String)keys.next();
                Object value = json.get(key);

                String camelCaseKey = JsonUtils.toCamelCase(key);

                String setterMethod = getSetterMethod(camelCaseKey);

                if(value instanceof JSONArray){
                    value = JsonUtils.toStringArray((JSONArray) value);
                }else if(value == JSONObject.NULL){
                    continue;
                }else if(value instanceof JSONObject){
                    Deserializer d = new Deserializer(Class.forName("UWaterloo." + key.substring(0, 1).toUpperCase() + key.substring(1)));
                    value = d.deserialize(((JSONObject) value));
                }

                Method setter = c.getDeclaredMethod(setterMethod, value.getClass());

                setter.invoke(newObject, value);

            }

        }catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return newObject;

    }

    private String getSetterMethod(String field){
        return "set" + field.substring(0,1).toUpperCase() + field.substring(1);
    }


}
