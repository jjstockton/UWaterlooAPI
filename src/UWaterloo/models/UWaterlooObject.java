package UWaterloo.models;

import java.lang.reflect.Field;

public class UWaterlooObject {

    protected Object getAttribute(String name) {

        Field f = getField(name);
        f.setAccessible(true);
        Object rawValue = null;
        try {
            rawValue = f.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (rawValue == null) {
            // Load value
            return rawValue;
        } else {
            return rawValue;
        }
    }

    private Field getField(String name) {
        Class c = this.getClass();
        do {
            try {
                return c.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                continue;
            }

        } while ((c = c.getSuperclass()) != null);

        throw new NoSuchFieldError();
    }
}
