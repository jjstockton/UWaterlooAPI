package UWaterloo.internal.json;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonObject extends UWaterlooJson {

    private String rawText;

    public JsonObject(String jsonText) {
        this.rawText = jsonText;
    }


    private String get(String key) {

        String noBrackets = rawText.substring(1, rawText.length() - 1);
        String noInnerObjects = noBrackets.replaceAll("\"[^\"]+\":\\{.*\\}", "");


        String regex = "\"" + key + "\":\\s*(\"(.*?)\"|[^,]+)";
        System.out.println(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m  = p.matcher(noInnerObjects);

        if(m.find()) {
            return m.group(1);
        }

        throw new RuntimeException("Couldn't find key: " + key);

    }

    public String getString(String key) {
        String value = get(key);
        if(value == null || value.equals("null")) {
            return null;
        }

        return String.valueOf(value.replaceAll("\"", ""));
    }

    public Boolean getBoolean(String key) {
        if(get(key) == null) {
            return null;
        }
        return Boolean.valueOf(get(key));
    }

    public Double getDouble(String key) {
        return Double.valueOf(get(key));
    }

    public List<?> getList(String key) {
        return null;
    }

    public JsonObject getJsonObject(String key) {
        String noBrackets = rawText.substring(1, rawText.length() - 1);

        String regex = "\"" + key + "\":\\s*(\\{.*\\})(?![^{]*})";
        Pattern p = Pattern.compile(regex);
        Matcher m  = p.matcher(noBrackets);

        if(m.find()) {
            return new JsonObject(m.group(1));
        }

        return null;
    }

    public String toString() {
        return this.rawText;
    }
}
