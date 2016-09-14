
import javax.naming.AuthenticationException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.json.*;

public class UWaterlooClient {

    private String apiKey;
    private static String BASE_URL = "https://api.uwaterloo.ca/v2/";
    private String keyString;

    //public UWaterlooClient() throws UnauthorizedException {
    //    throw new UnauthorizedException("API key is required.");
    //}

    public UWaterlooClient(String key) {
        if(isValidKey(key)){
            this.apiKey = key;
            this.keyString = "?key=" + key;
        }
    }

    private static boolean isValidKey(String key) {

        String url = BASE_URL + "codes/units.json?key=" + key;

        try {
            URL website = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) website.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new HttpResponseException(responseCode, conn.getErrorStream());
            }

            conn.disconnect();
        }catch(IOException e){
            System.err.println(e);
        }

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

    private JSONObject getJson(String endpoint) {

        String url = BASE_URL + endpoint + ".json" + keyString;

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
            if(metaResponseCode != 200){
                throw new HttpResponseException(metaResponseCode, site.openStream());
            }

            conn.disconnect();
        } catch(IOException e ){

        }


        return json;

    }


    protected static JSONObject getJson(InputStream input){

        String jsonString = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            //String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                jsonString += line;
            }
        }catch(IOException e){
            System.err.println(e);
        }

        return new JSONObject(jsonString);

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

    public ArrayList<Term> getTerms() throws IOException, HttpResponseException {

        JSONArray jsonTerms = getJson("codes/terms").getJSONArray("data");

        ArrayList<Term> terms = new ArrayList<>();

        for(int i = 0; i < jsonTerms.length(); i++) {

            JSONObject jsonTerm = jsonTerms.getJSONObject(i);

            terms.add(new Term(jsonTerm.getString("abbreviation"), jsonTerm.getString("description")));

        }

        return terms;

    }


    public ArrayList<Course> getCourses(int term){

        JSONArray jsonCourses = getJson("terms/" + term + "/courses").getJSONArray("data");

        ArrayList<Course> courses = new ArrayList<>();

        for(int i = 0; i < jsonCourses.length(); i++) {

            JSONObject jsonCourse = jsonCourses.getJSONObject(i);

            courses.add(new Course(jsonCourse.getString("subject"), jsonCourse.getString("catalog_number"), jsonCourse.getDouble("units"), jsonCourse.getString("title")));

        }

        return courses;


    }



}


class HttpResponseException extends RuntimeException {

    public HttpResponseException() { super(); }
    public HttpResponseException(String message) { super(message); }
    public HttpResponseException(String message, Throwable cause) { super(message, cause); }
    public HttpResponseException(Throwable cause) { super(cause); }


    public HttpResponseException(int responseCode, InputStream errorStream) {
        this(getErrorMessage(responseCode, errorStream));
    }

    private static String getErrorMessage(int responseCode, InputStream errorStream) {

        String errorMessage ;

        switch (responseCode) {
            case 401:
                errorMessage = "Invalid API Key.";
                break;
            case 511:
                errorMessage = "API Key is required.";
                break;
            default:
                JSONObject obj = UWaterlooClient.getJson(errorStream);
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

class Term {
    public String abbreviation;
    public String description;

    public Term(String abbreviation, String description){
        this.abbreviation = abbreviation;
        this.description = description;
    }



}

class Course {

    public String subject;
    public String number;
    public double units;
    public String name;

    public Course(String subject, String number, double units, String name){

        this.subject = subject;
        this.number = number;
        this.units = units;
        this.name = name;

    }



}
