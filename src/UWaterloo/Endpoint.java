package UWaterloo;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static UWaterloo.JsonUtils.getJson;

public class Endpoint {

    private static final String BASE_URL = "https://api.uwaterloo.ca/v2";

    private final String url;
    final Class c;

    Endpoint(String uri, Class c) {
        this.url = BASE_URL + uri + ".json";
        this.c = c;
    }

    Object getData(String[] args, String key) {

        String authenticatedUrl = fillArguments(args) + "?key=" + key;

        System.out.println(authenticatedUrl);

        JSONObject jsonObject = getJson(authenticatedUrl).getJSONObject("data");

        Deserializer d = new Deserializer(this.c);

        return d.deserialize(jsonObject);

    }

    private String fillArguments(String... args) {

        String filledUrl = this.url;
        Pattern p = Pattern.compile("\\{.*?\\}");
        Matcher m = p.matcher(this.url);

        int i;
        for(i = 0; m.find(); i++) {
            filledUrl = filledUrl.replaceFirst("\\{.*?\\}", args[i]);
        }

        if(i < args.length) {
            throw new IllegalArgumentException();
        }

        return filledUrl;

    }

}
