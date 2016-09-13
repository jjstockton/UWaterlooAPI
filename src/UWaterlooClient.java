
import javax.naming.AuthenticationException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

import org.json.*;

public class UWaterlooClient {

    private String apiKey;
    private static String BASE_URL = "https://api.uwaterloo.ca/v2/";
    private String keyString;

    //public UWaterlooClient() throws UnauthorizedException {
    //    throw new UnauthorizedException("API key is required.");
    //}

    public UWaterlooClient(String key) throws IOException, HttpResponseException {
        if(validKey(key)){
            this.apiKey = key;
            this.keyString = "?key=" + key;
        }
    }

    private static boolean validKey(String key) throws IOException, HttpResponseException {

        String url = BASE_URL + "codes/units.json?key=" + key;
        System.out.println(url);
        URL website = new URL(url);

        HttpURLConnection conn = (HttpURLConnection)website.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if(responseCode != 200) {
            throw new HttpResponseException(responseCode, conn.getErrorStream());
        }

        //This is necessary because the API sometimes returns a response code that is not correct
        int metaResponseCode = getResponseCode(conn.getInputStream());
        if(metaResponseCode != 200){
            throw new HttpResponseException(metaResponseCode, website.openStream());
        }

        conn.disconnect();

        return true;

    }

    private static int getResponseCode(InputStream response) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        String text= "";
        String line;
        while((line = br.readLine()) != null){
            text += line;
        }

        JSONObject obj = new JSONObject(text);

        return obj.getJSONObject("meta").getInt("status");
    }

    private static String getResponse(InputStream input) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        String text = "";
        String line;

        System.out.println("Response:");
        while((line = br.readLine()) != null){
            text += line;
        }

        return text;
    }



    public ArrayList<Unit> getUnits() throws IOException, HttpResponseException {

        String url = BASE_URL + "codes/units.json" + keyString;
        URL website = new URL(url);

        HttpURLConnection conn = (HttpURLConnection)website.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if(responseCode != 200) {
            throw new HttpResponseException(responseCode, conn.getErrorStream());
        }

        String rawtext = getResponse(website.openStream());

        System.out.println(rawtext);

        JSONObject obj = new JSONObject(rawtext);

        JSONArray jsonUnits = obj.getJSONArray("data");



        ArrayList<Unit> units = new ArrayList<>();

        for(int i = 0; i < jsonUnits.length(); i++) {

            JSONObject jsonUnit = jsonUnits.getJSONObject(i);

            units.add(new Unit(jsonUnit.getString("unit_code"), jsonUnit.getString("group_code"), jsonUnit.getString("unit_short_name"), jsonUnit.getString("unit_full_name")));

        }



        return units;

    }
}


class HttpResponseException extends Exception {

    public HttpResponseException() { super(); }
    public HttpResponseException(String message) { super(message); }
    public HttpResponseException(String message, Throwable cause) { super(message, cause); }
    public HttpResponseException(Throwable cause) { super(cause); }


    public HttpResponseException(int responseCode, InputStream errorStream) throws IOException, HttpResponseException {
        this(getErrorMessage(responseCode, errorStream));
    }

    private static String getErrorMessage(int responseCode, InputStream errorStream) throws IOException {

        String errorMessage ;

        switch (responseCode) {
            case 401:
                errorMessage = "Invalid API Key.";
                break;
            case 511:
                errorMessage = "API Key is required.";
                break;
            default:

                BufferedReader br = new BufferedReader(new InputStreamReader(errorStream));
                String text= "";
                String line;
                while((line = br.readLine()) != null){
                    text += line;
                }

                JSONObject obj = new JSONObject(text);

                errorMessage = obj.getJSONObject("meta").getString("message");
        }

        return errorMessage;

    }


}

class Unit {

    public String unitCode;
    public String groupCode;
    public String unitShortName;
    public String unitFullName;

    public Unit(String unit_code, String group_code, String unit_short_name, String unit_full_name) {

        this.unitCode = unit_code;
        this.groupCode = group_code;
        this.unitShortName = unit_short_name;
        this.unitFullName = unit_full_name;
    }
}
