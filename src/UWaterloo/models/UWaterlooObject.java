package UWaterloo.models;

import UWaterloo.internal.json.JsonObject;

import java.util.List;

public class UWaterlooObject extends JsonObject {

    public UWaterlooObject(JsonObject json) {
        super(json.toString());
    }

    @Override
    public <T> T get(String key, Class<T> type) {

        T value = super.get(key, type);

        if(value == null) {
            System.out.println("Lazy load!!");
            return value;
        }

        return value;
    }

    @Override
    public <T> List<T> getList(String name, Class<T> type) {

        List<T> value = super.getList(name, type);

        if(value == null) {
            System.out.println("Lazy load!!");
            return value;
        }

        return value;

    }
}
