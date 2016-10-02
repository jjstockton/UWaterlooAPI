package UWaterloo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static UWaterloo.JsonUtils.isEmpty;
import static UWaterloo.StringUtils.toCamelCase;

class Deserializer {

    private Class baseClass;

    protected Deserializer(Class c) {
        this.baseClass = c;
    }

    Object deserialize(Object obj) {
        return deserialize(obj, this.baseClass);
    }

    static Object deserialize(Object inputObj, Class targetClass) {

        return deserialize(inputObj, null, targetClass);
    }

    static Object deserialize(Object from, Object parentObject, Class targetClass) {

        if(from instanceof JSONObject) {
            Object newObject = null;
            try {
                if (parentObject == null) {
                    newObject = targetClass.newInstance();
                } else {
                    Constructor ctor = targetClass.getDeclaredConstructor(parentObject.getClass());
                    newObject = ctor.newInstance(parentObject);
                }
            }catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
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
                String innerClassName = c.getName() + "$" + key.substring(0, 1).toUpperCase() + key.substring(1);
                if(rawValue instanceof JSONObject || rawValue instanceof JSONArray && classExists(innerClassName)) {
                    Class<?> targetClass = Class.forName(innerClassName);
                    value = deserialize(rawValue, obj, targetClass);
                } else if(rawValue instanceof JSONArray) {
                    JSONArray array = (JSONArray) rawValue;
                    if(isEmpty(array)) {
                        value = new ArrayList<>();
                    } else {
                        value = deserialize(rawValue, array.get(0).getClass());
                    }
                } else {
                    value = deserialize(rawValue, null, rawValue.getClass());
                }

                String camelCaseKey = toCamelCase(key);
                String setterMethod = getSetterMethod(camelCaseKey);
                Method setter;
                try {
                    setter = c.getDeclaredMethod(setterMethod, value.getClass());
                }catch(NullPointerException e) {
                    continue;
                } catch (NoSuchMethodException e) {
                    setter = c.getSuperclass().getDeclaredMethod(setterMethod, value.getClass());
                }
                setter.invoke(obj, value);
            }
        }catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static String getSetterMethod(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private static boolean classExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
