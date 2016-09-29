package UWaterloo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

class Deserializer {

    private Class c;
    private Object instance;

    Deserializer(Class c) {
        this.c = c;
    }

    private Deserializer(Object instance) {
        this.c = instance.getClass();
        this.instance = instance;
    }

    Object deserialize(Object obj) {
        if (obj instanceof JSONObject) {
            try {
                Object newObject;
                if(instance == null) {
                    newObject = c.newInstance();
                } else {
                    newObject = instance;
                }
                return createObject((JSONObject) obj, newObject);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (obj instanceof JSONArray) {
            int length = ((JSONArray) obj).length();
            Object[] objects = new Object[length];

            for (int i = 0; i < length; i++) {
                objects[i] = deserialize(((JSONArray) obj).get(i));
            }

            Class arrayClass = null;
            try {
                arrayClass = (Class) Class.forName("[L" + objects[0].getClass().getName() + ";");
            }catch(ClassNotFoundException e) {
                e.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException e) {
                // To do: make it get class of setter method
                arrayClass = String[].class;
            }
            return Arrays.copyOf(objects, objects.length, arrayClass);

        } else if (obj == JSONObject.NULL) {
            return null;
        } else {
            return obj;
        }

        throw new RuntimeException("Deserializer failed somehow");
    }

    private Object createObject(JSONObject json, Object inst) {
        try {
            Iterator keys = json.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object rawValue = json.get(key);


                Class keyClass;
                Object value = null;
                try {
                    Class<?> innerClass = Class.forName(this.c.getName() + "$" + key.substring(0, 1).toUpperCase() + key.substring(1));
                    Constructor ctor = innerClass.getDeclaredConstructor(c);

                    Deserializer d = new Deserializer(ctor.newInstance(inst));

                    value = d.deserialize(rawValue);

                } catch (ClassNotFoundException e) {
                    keyClass = rawValue.getClass();
                    Deserializer d = new Deserializer(keyClass);
                    value = d.deserialize(rawValue);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                String camelCaseKey = JsonUtils.toCamelCase(key);
                String setterMethod = getSetterMethod(camelCaseKey);
                Method setter;
                try {
                    setter = this.c.getDeclaredMethod(setterMethod, value.getClass());
                }catch(NullPointerException e) {
                    continue;
                }
                setter.invoke(inst, value);
            }
        }catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return inst;
    }

    private static String getSetterMethod(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}
