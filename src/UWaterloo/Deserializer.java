package UWaterloo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

class Deserializer {

    private Class baseClass;

    Deserializer(Class c) {
        this.baseClass = c;
    }

    Object deserialize(Object obj) {
        return deserialize(obj, this.baseClass);
    }

    static Object deserialize(Object inputObj, Class targetClass) {

        return deserialize(inputObj, new Object(), targetClass);
    }

    static Object deserialize(Object from, Object parentObject, Class targetClass) {


        if(from instanceof JSONObject) {
            Object newObject = null;
            try {
                Constructor ctor = targetClass.getDeclaredConstructor(parentObject.getClass());
                newObject = ctor.newInstance(parentObject);
            } catch (NoSuchMethodException  e) {
                try {
                    newObject = targetClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            } catch (NullPointerException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return populateCustomObject((JSONObject) from, newObject);
        } else if (from instanceof JSONArray) {

            List<Object> objects = new ArrayList<>();

            for(Object o : (JSONArray) from) {
                objects.add(deserialize(o, parentObject, targetClass));
            }

            return objects;
        } else if(from == JSONObject.NULL) {
            return null;
        }

        return from;
    }

    private static Object populateCustomObject(JSONObject json, Object obj) {
        Class c = obj.getClass();
        try {
            Iterator keys = json.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object rawValue = json.get(key);

                Object value;
                try {
                    String innerClassName = c.getName() + "$" + key.substring(0, 1).toUpperCase() + key.substring(1);
                    Class<?> innerClass = Class.forName(innerClassName);

                    value = deserialize(rawValue, obj, innerClass);

                } catch (ClassNotFoundException e) {
                    value = deserialize(rawValue, rawValue.getClass());
                }

                String camelCaseKey = JsonUtils.toCamelCase(key);
                String setterMethod = getSetterMethod(camelCaseKey);
                Method setter;
                try {
                    setter = c.getDeclaredMethod(setterMethod, value.getClass());
                }catch(NullPointerException e) {
                    continue;
                }

                setter.invoke(obj, value);
            }
        }catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static String getSetterMethod(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}
