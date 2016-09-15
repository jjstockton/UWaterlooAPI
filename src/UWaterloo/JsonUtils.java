package UWaterloo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

final class JsonUtils {

    static JSONObject getJson(InputStream input) {

        String jsonString = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            //String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                jsonString += line;
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        //System.out.println(jsonString);
        return new JSONObject(jsonString);

    }

    static JSONObject getJson(String url) {

        //String url = BASE_URL + endpoint + ".json" + keyString;

        JSONObject json = null;

        try {

            URL site = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) site.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();

            int responseCode = conn.getResponseCode();


            if (responseCode != 200) {
                throw new HttpResponseException(responseCode, conn.getErrorStream());
            }


            json = getJson(site.openStream());
            int metaResponseCode = json.getJSONObject("meta").getInt("status");
            if (metaResponseCode != 200) {
                throw new HttpResponseException(metaResponseCode, site.openStream());
            }

            conn.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


        return json;

    }

    static String toCamelCase(String input) {

        StringBuilder str = new StringBuilder(input);

        while (str.toString().contains("_")) {

            str.setCharAt(str.indexOf("_") + 1, Character.toUpperCase(str.charAt(str.indexOf("_") + 1)));
            str.deleteCharAt(str.indexOf("_"));
        }
        return str.toString();
    }

    static String[] toStringArray(JSONArray jsonArray) {

        String[] array = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            array[i] = (String) jsonArray.get(i);
        }

        return array;

    }


}
