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

        try {
            if(from instanceof JSONObject) {
                Object newObject;

                    if (parentObject == null) {
                        newObject = targetClass.newInstance();
                    } else {
                        Constructor ctor = targetClass.getDeclaredConstructor(parentObject.getClass());
                        newObject = ctor.newInstance(parentObject);
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
        }catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
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
                Class valueType;
                if(value == null) {
                    valueType = null;
                } else {
                    valueType = value.getClass();
                }
                Method setter = getSetterMethod(c, camelCaseKey, valueType);

                if(value == null) {
                    value = setter.getParameterTypes()[0].newInstance();
                }
                setter.setAccessible(true);
                setter.invoke(obj, value);

            }
        }catch(IllegalAccessException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static Method getSetterMethod(Class c, String field, Class parameterType) {
        String name = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
        Class currentClass = c;
        Method setter = null;
        while(parameterType == null) {
            for(Method m : currentClass.getDeclaredMethods()) {
                if (m.getName().equals(name)) {
                    return m;
                }
            }
            currentClass = c.getSuperclass();
        }

        try {
            return c.getDeclaredMethod(name, parameterType);
        } catch (NoSuchMethodException e) {
            currentClass = c;
            while(setter == null) {
                currentClass = currentClass.getSuperclass();
                try {
                    return currentClass.getDeclaredMethod(name, parameterType);
                } catch(NoSuchMethodException e1) {
                    continue;
                }
            }
        }
        return setter;
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
