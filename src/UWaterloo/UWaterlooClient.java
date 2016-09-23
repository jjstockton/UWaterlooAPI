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
            e.printStackTrace();
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

        Deserializer d = new Deserializer(Unit.class);

        String url = buildUrl("codes", "units");

        JSONArray jsonUnits = getJson(url).getJSONArray("data");

        ArrayList<Unit> units = new ArrayList<>();

        for(int i = 0; i < jsonUnits.length(); i++) {

            JSONObject jsonUnit = jsonUnits.getJSONObject(i);

            units.add((Unit) d.deserialize(jsonUnit));

        }

        return units;

    }

    public ArrayList<Term> getTerms() {

        Deserializer d = new Deserializer(Term.class);

        String url = buildUrl("codes", "terms");

        JSONArray jsonTerms = getJson(url).getJSONArray("data");

        ArrayList<Term> terms = new ArrayList<>();

        for(int i = 0; i < jsonTerms.length(); i++) {

            JSONObject jsonTerm = jsonTerms.getJSONObject(i);

            terms.add((Term) d.deserialize(jsonTerm));

        }

        return terms;

    }


    public ArrayList<Course> getCourses(int term){

        Deserializer d = new Deserializer(Course.class);

        String url = buildUrl("terms", String.valueOf(term), "courses");

        JSONArray jsonCourses = getJson(url).getJSONArray("data");

        ArrayList<Course> courses = new ArrayList<>();

        for(int i = 0; i < jsonCourses.length(); i++) {

            JSONObject jsonCourse = jsonCourses.getJSONObject(i);

            courses.add((Course) d.deserialize(jsonCourse));

        }

        return courses;

    }

    public ArrayList<Course> getCourses(){

        Deserializer d = new Deserializer(Course.class);

        String url = buildUrl("courses");

        JSONArray jsonCourses = getJson(url).getJSONArray("data");

        ArrayList<Course> courses = new ArrayList<>();

        for(int i = 0; i < jsonCourses.length(); i++) {

            JSONObject jsonCourse = jsonCourses.getJSONObject(i);

            courses.add((Course) d.deserialize(jsonCourse));

        }

        return courses;
    }

    public Course getCourse(String subject, String catalogNumber){

        String url = buildUrl("courses", subject, catalogNumber);

        JSONObject course = getJson(url).getJSONObject("data");

        Deserializer d = new Deserializer(Course.class);

        return (Course) d.deserialize(course);

    }

    public ArrayList<Schedule> getSchedules(String subject, String catalogNumber) {
        Deserializer d = new Deserializer(Schedule.class);
        String url = buildUrl("courses", subject, catalogNumber, "schedule");
        JSONArray jsonSchedules = getJson(url).getJSONArray("data");
        ArrayList<Schedule> schedules = new ArrayList<>();

        for(int i = 0; i < jsonSchedules.length(); i++) {
            JSONObject jsonSchedule = jsonSchedules.getJSONObject(i);
            schedules.add((Schedule) d.deserialize(jsonSchedule));
        }

        return schedules;
    }

    private String buildUrl(String... params){

        String url = BASE_URL;

        url = url + String.join("/", params) + ".json?key=" + apiKey;

        return url;

    }

}


class HttpResponseException extends RuntimeException {

    public HttpResponseException() { super(); }
    private HttpResponseException(String message) { super(message); }
    public HttpResponseException(String message, Throwable cause) { super(message, cause); }
    public HttpResponseException(Throwable cause) { super(cause); }


     HttpResponseException(int responseCode, InputStream errorStream) {
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

