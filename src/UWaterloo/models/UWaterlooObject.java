package UWaterloo.models;

import java.lang.reflect.Field;

public class UWaterlooObject {

    protected Object getAttribute(String name) {

        //Object value = null;

        Field f = getField(name);
        f.setAccessible(true);
        Object rawValue = null;
        try {
            rawValue = f.get(this);
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }

        if(rawValue == null) {
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
            } catch(NoSuchFieldException e) {
                continue;
            }

        } while((c = this.getClass().getSuperclass()) != null);

        throw new IllegalArgumentException();
    }
}
