package UWaterloo.internal.json;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonObject extends UWaterlooJson {

    private String rawText;

    public JsonObject(String jsonText) {
        this.rawText = jsonText;
    }

    public <T> T get(String key, Class<T> type) {

        if(type == String.class) {
            return (T) getString(key);
        }

        return (T) getRaw(key);
    }

    public String getRaw(String key) {

        String regex = "\"" + key + "\":\\s*(\"(.*?)\"|[^,]+)";
        Pattern p = Pattern.compile(regex);
        Matcher m  = p.matcher(clean(rawText));

        if(m.find()) {
            return m.group(1);
        }

        throw new RuntimeException("Couldn't find key: " + key);

    }

    public String getString(String key) {
        String value = getRaw(key);
        if(value == null || value.equals("null")) {
            return null;
        }

        return String.valueOf(value.replaceAll("\"", ""));
    }

    public Boolean getBoolean(String key) {
        if(getRaw(key) == null) {
            return null;
        }
        return Boolean.valueOf(getRaw(key));
    }

    public Double getDouble(String key) {
        return Double.valueOf(getRaw(key));
    }

    public Integer getInt(String key) {
        return Integer.valueOf(getRaw(key));
    }


    public <T> List<T> getList(String key, Class<T> type) {

        String regex = "\"" + key + "\":\\s*(\\[.*?\\])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(clean(rawText));

        List<T> retVal = new ArrayList<>();

        if(m.find()) {
            String rawArray = m.group(1);
            rawArray = rawArray.substring(1, rawArray.length() - 1);

            String[] strArray = rawArray.split(",");

            for(String value : strArray) {
                retVal.add((T) value.replace("\"", ""));
            }
            return retVal;
        }

        throw new RuntimeException("Couldn't find key: " + key);

    }

    public JsonObject getJsonObject(String key) {

        String regex = "\"" + key + "\":\\s*(\\{.*\\})(?![^{]*})";
        Pattern p = Pattern.compile(regex);
        Matcher m  = p.matcher(rawText.substring(1, rawText.length() - 1));

        if(m.find()) {
            return new JsonObject(m.group(1));
        }

        return null;
    }

    private String clean(String text) {

        return text.substring(1, text.length() - 1).replaceAll("\\{.*\\}", "removed");

    }

    public String toString() {
        return this.rawText;
    }
}
