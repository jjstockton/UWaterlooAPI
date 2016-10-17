package UWaterloo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import UWaterloo.internal.http.ApiResponseException;
import UWaterloo.models.*;

public class UWaterlooClient {

    private String apiKey;
    private static String BASE_URL = "https://api.uwaterloo.ca/v2/";


    public UWaterlooClient(String key) {
        if (isValidKey(key)) {
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
                throw new ApiResponseException(responseCode, conn.getErrorStream());
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    public List<Unit> getUnits() {
        Object args[] = {};
        return (List<Unit>) Endpoint.CODES_UNITS.getData(args, apiKey);
    }

    public List<Term> getTerms() {
        Object args[] = {};
        return (List<Term>) Endpoint.CODES_TERMS.getData(args, apiKey);
    }

    public List<Course> getCourses() {
        Object args[] = {};
        return (List<Course>) Endpoint.COURSES.getData(args, apiKey);
    }

    public List<Course> getCourses(String subject) {
        String args[] = {subject};
        return (List<Course>) Endpoint.COURSES_SUBJECT.getData(args, apiKey);
    }

    public Course getCourse(String courseId) {
        String args[] = {courseId};
        return (Course) Endpoint.COURSES_COURSEID.getData(args, apiKey);
    }

    public List<Schedule> getSchedule(int classNumber) {
        Integer args[] = {classNumber};
        return (List<Schedule>) Endpoint.COURSES_CLASSNUMBER_SCHEDULE.getData(args, apiKey);
    }

    public List<Course> getCourses(int term) {
        Object args[] = {term};
        return (List<Course>) Endpoint.TERMS_TERM_COURSES.getData(args, apiKey);
    }

    public Course getCourse(String subject, String catalogNumber) {
        Object args[] = {subject, catalogNumber};
        return (Course) Endpoint.COURSES_SUBJECT_CATALOGNUMBER.getData(args, apiKey);
    }

    public List<Schedule> getSchedules(String subject, String catalogNumber) {
        Object args[] = {subject, catalogNumber};
        return (List<Schedule>) Endpoint.COURSES_SUBJECT_CATALOGNUMBER_SCHEDULE.getData(args, apiKey);
    }

    public ExamSchedule getExamSchedule(String subject, String catalogNumber) {
        String args[] = {subject, catalogNumber};
        return (ExamSchedule) Endpoint.COURSES_SUBJECT_CATALOGNUMBER_EXAMSCHEDULE.getData(args, apiKey);
    }
}

