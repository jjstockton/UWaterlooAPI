package UWaterloo;

import java.net.*;
import java.io.*;
import java.util.List;

import UWaterloo.models.*;
import org.json.*;

import static UWaterloo.JsonUtils.*;

public class UWaterlooClient {

    private String apiKey;
    private static String BASE_URL = "https://api.uwaterloo.ca/v2/";


    public UWaterlooClient(String key) {
        if(isValidKey(key)){
            this.apiKey = key;
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

    public List<Unit> getUnits() {
        Object args[] = {};
        return (List<Unit>) Endpoints.CODES_UNITS.getData(args, apiKey);
    }

    public List<Term> getTerms() {
        Object args[] = {};
        return (List<Term>) Endpoints.CODES_TERMS.getData(args, apiKey);
    }

    public List<Course> getCourses() {
        Object args[] = {};
        return (List<Course>) Endpoints.COURSES.getData(args, apiKey);
    }

    public List<Course> getCourses(String subject) {
        String args[] = {subject};
        return (List<Course>) Endpoints.COURSES_SUBJECT.getData(args, apiKey);
    }

    public Course getCourse(String courseId) {
        String args[] = {courseId};
        return (Course) Endpoints.COURSES_COURSEID.getData(args, apiKey);
    }

    public List<Schedule> getSchedule(int classNumber) {
        Integer args[] = {classNumber};
        return (List<Schedule>) Endpoints.COURSES_CLASSNUMBER_SCHEDULE.getData(args, apiKey);
    }

    public List<Course> getCourses(int term){
        Object args[] = {term};
        return (List<Course>) Endpoints.TERMS_TERM_COURSES.getData(args, apiKey);
    }

    public Course getCourse(String subject, String catalogNumber){
        Object args[] = {subject, catalogNumber};
        return (Course) Endpoints.COURSES_SUBJECT_CATALOGNUMBER.getData(args, apiKey);
    }

    public List<Schedule> getSchedules(String subject, String catalogNumber) {
        Object args[] = {subject, catalogNumber};
        return (List<Schedule>) Endpoints.COURSES_SUBJECT_CATALOGNUMBER_SCHEDULE.getData(args, apiKey);
    }

    public ExamSchedule getExamSchedule(String subject, String catalogNumber) {
        String args[] = {subject, catalogNumber};
        return (ExamSchedule) Endpoints.COURSES_SUBJECT_CATALOGNUMBER_EXAMSCHEDULE.getData(args, apiKey);
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

