package UWaterloo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

class Deserializer {

    protected Class c;

     Deserializer(Class c) {
         this.c = c;
     }

    Object deserialize(JSONObject json) {

        Object newObject = null;
        try {
            newObject = c.newInstance();
        }catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ObjectDeserializer objectDeserializer = new ObjectDeserializer(newObject);

        return objectDeserializer.deserialize(json);
    }

     protected static String getSetterMethod(String field){
        return "set" + field.substring(0,1).toUpperCase() + field.substring(1);
    }

}

class ObjectDeserializer extends Deserializer {

    private Object instance;
    private JSONObject json;

    public ObjectDeserializer(Object inst) {
        super(inst.getClass());
        this.instance = inst;
    }

    Object deserialize(JSONObject json) {

        try {

            Iterator keys = json.keys();

            while(keys.hasNext()){

                String key = (String)keys.next();
                Object value = json.get(key);

                String camelCaseKey = JsonUtils.toCamelCase(key);

                String setterMethod = Deserializer.getSetterMethod(camelCaseKey);

                if(value instanceof JSONArray){
                    value = JsonUtils.toStringArray((JSONArray) value);
                }else if(value == JSONObject.NULL){
                    continue;
                }else if(value instanceof JSONObject){
                    Class<?> innerClass = Class.forName(this.c.getName() + "$" + key.substring(0, 1).toUpperCase() + key.substring(1));
                    Constructor ctor = innerClass.getDeclaredConstructor(c);

                    Object inst = ctor.newInstance(this.instance);

                    Deserializer d = new ObjectDeserializer(inst);
                    value = d.deserialize(((JSONObject) value));
                }

                Method setter = this.c.getDeclaredMethod(setterMethod, value.getClass());

                setter.invoke(instance, value);

            }

        }catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return this.instance;

    }
}
