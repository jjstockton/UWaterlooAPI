package UWaterloo;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import org.json.*;

import static UWaterloo.JsonUtils.*;

public class UWaterlooClient {

    private String apiKey;
    private static String BASE_URL = "https://api.uwaterloo.ca/v2/";
    private String keyString;


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







    public ArrayList<Unit> getUnits() {

        String endpoint = "codes/units";
        String url = BASE_URL + endpoint +".json" + keyString;

        JSONArray jsonUnits = getJson(url).getJSONArray("data");

        ArrayList<Unit> units = new ArrayList<>();

        for(int i = 0; i < jsonUnits.length(); i++) {

            JSONObject jsonUnit = jsonUnits.getJSONObject(i);

            units.add(new Unit(jsonUnit.getString("unit_code"), jsonUnit.getString("group_code"), jsonUnit.getString("unit_short_name"), jsonUnit.getString("unit_full_name")));

        }

        return units;

    }

    public ArrayList<Term> getTerms() throws IOException, HttpResponseException {

        String endpoint = "codes/terms";
        String url = BASE_URL + endpoint +".json" + keyString;

        JSONArray jsonTerms = getJson(url).getJSONArray("data");

        ArrayList<Term> terms = new ArrayList<>();

        for(int i = 0; i < jsonTerms.length(); i++) {

            JSONObject jsonTerm = jsonTerms.getJSONObject(i);

            terms.add(new Term(jsonTerm.getString("abbreviation"), jsonTerm.getString("description")));

        }

        return terms;

    }


    public ArrayList<Course> getCourses(int term){

        String endpoint = "terms/" + term + "/courses";
        String url = BASE_URL + endpoint +".json" + keyString;

        JSONArray jsonCourses = getJson(url).getJSONArray("data");

        ArrayList<Course> courses = new ArrayList<>();

        for(int i = 0; i < jsonCourses.length(); i++) {

            JSONObject jsonCourse = jsonCourses.getJSONObject(i);

            courses.add(new Course(jsonCourse.getString("subject"), jsonCourse.getString("catalog_number"), jsonCourse.getDouble("units"), jsonCourse.getString("title")));

        }

        return courses;


    }

    public ArrayList<Course> getCourses(){

        String endpoint = "courses";
        String url = BASE_URL + endpoint +".json" + keyString;

        JSONArray jsonCourses = getJson(url).getJSONArray("data");

        ArrayList<Course> courses = new ArrayList<>();

        for(int i = 0; i < jsonCourses.length(); i++) {

            JSONObject jsonCourse = jsonCourses.getJSONObject(i);

            courses.add(new Course(jsonCourse.getString("subject"), jsonCourse.getString("catalog_number"), jsonCourse.getString("title"), jsonCourse.getInt("course_id")));

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
                JSONObject obj = getJson(errorStream);
                errorMessage = obj.getJSONObject("meta").getString("message");
        }

        return errorMessage;

    }


}

