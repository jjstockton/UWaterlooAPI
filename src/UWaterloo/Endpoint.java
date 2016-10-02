package UWaterloo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static UWaterloo.Deserializer.deserialize;
import static UWaterloo.JsonUtils.getJson;

class Endpoint {

    private static final String BASE_URL = "https://api.uwaterloo.ca/v2";

    private final String url;
    final Class c;

    Endpoint(String uri, Class c) {
        this.url = BASE_URL + uri + ".json";
        this.c = c;
    }

    Object getData(Object[] args, String key) {

        String authenticatedUrl = fillArguments(args) + "?key=" + key;
        Object json = getJson(authenticatedUrl).get("data");

        return deserialize(json, c);

    }

    private String fillArguments(Object... args) {

        String filledUrl = this.url;
        Pattern p = Pattern.compile("\\{.*?\\}");
        Matcher m = p.matcher(this.url);

        int i;
        for(i = 0; m.find(); i++) {
            filledUrl = filledUrl.replaceFirst("\\{.*?\\}", args[i].toString());
        }

        if(i < args.length) {
            throw new IllegalArgumentException();
        }

        return filledUrl;

    }
}
