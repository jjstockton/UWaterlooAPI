package UWaterloo.internal.utils;

import UWaterloo.internal.http.ApiResponseException;
import UWaterloo.internal.json.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public final class JsonUtils {

    public static JsonObject getJson(InputStream input) {
        String jsonString = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                jsonString += line;
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        return new JsonObject(jsonString);

    }

    public static JsonObject getJson(String url) {

        JsonObject json = null;

        try {

            URL site = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) site.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();

            int responseCode = conn.getResponseCode();


            if (responseCode != 200) {
                throw new ApiResponseException(responseCode, conn.getErrorStream());
            }


            json = getJson(site.openStream());
            int metaResponseCode = json.getJsonObject("meta").getInt("status");
            if (metaResponseCode != 200) {
                throw new ApiResponseException(metaResponseCode, site.openStream());
            }

            conn.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


        return json;

    }

}
