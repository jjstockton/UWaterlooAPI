package UWaterloo;

import UWaterloo.internal.json.JsonArray;
import UWaterloo.internal.json.JsonObject;
import UWaterloo.models.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static UWaterloo.internal.utils.JsonUtils.getJson;

public enum Endpoint {

    // Food Services
    FOODSERVICES_MENU("/foodservices/menu", Object.class),
    FOODSERVICES_NOTES("/foodservices/notes", Object.class),
    FOODSERVICES_DIETS("/foodservices/diets", Object.class),
    FOODSERVICES_OUTLETS("/foodservices/outlets", Object.class),
    FOODSERVICES_LOCATIONS("/foodservices/locations", Object.class),
    FOODSERVICES_WATCARD("/foodservices/watcard", Object.class),
    FOODSERVICES_ANNOUNCEMENTS("/foodservices/announcements", Object.class),
    FOODSERVICES_PRODUCTS_PRODUCTID("/foodservices/products/product_id", Object.class),
    FOODSERVICES_YEAR_WEEK_MENU("/foodservices/{year}/{week}/menu", Object.class),
    FOODSERVICES_YEAR_WEEK_NOTES("/foodservices/{year}/{week}/notes", Object.class),
    FOODSERVICES_YEAR_WEEK_ANNOUNCEMENTS("/foodservices/{year}/{week}/announcements", Object.class),

    //FEDS
    FEDS_EVENTS("/feds/events", Object.class),
    FEDS_EVENTS_ID("/feds/events/{id}", Object.class),
    FEDS_LOCATIONS("/feds/locations", Object.class),

    // Course
    COURSES("/courses", Course.class),
    COURSES_SUBJECT("/courses/{subject}", Course.class),
    COURSES_COURSEID("/courses/{course_id}", Course.class),
    COURSES_CLASSNUMBER_SCHEDULE("/courses/{class_number}/schedule", Schedule.class),
    COURSES_SUBJECT_CATALOGNUMBER("/courses/{subject}/{catalog_number}", Course.class),
    COURSES_SUBJECT_CATALOGNUMBER_SCHEDULE("/courses/{subject}/{catalog_number}/schedule", Schedule.class),
    COURSES_SUBJECT_CATALOGNUMBER_PREREQUISITES("/courses/{subject}/{catalog_number}/prerequisites", Object.class),
    COURSES_SUBJECT_CATALOGNUMBER_EXAMSCHEDULE("/courses/{subject}/{catalog_number}/examschedule", ExamSchedule.class),

    // Awards/Scholarships
    AWARDS_GRADUATE("/awards/graduate", Object.class),
    AWARDS_UNDERGRADUATE("/awards/undergraduate", Object.class),

    // Events
    EVENTS("/events", Object.class),
    EVENTS_SITE("/events/{site}", Object.class),
    EVENTS_SITE_ID("/events/{site}/{id}", Object.class),
    EVENTS_HOLIDAYS("/events/holidays", Object.class),

    // Blogs
    BLOGS_SITE("/blogs/{site}", Object.class),
    BLOGS_SITE_ID("/blogs/{site}/{id}", Object.class),

    // News
    NEWS("/news", Object.class),
    NEWS_SITE("/news/{site}", Object.class),
    NEWS_SITE_ID("/news/{site}/{id}", Object.class),

    // Opportunities/Jobs
    OPPORTUNITIES("/opportunities", Object.class),
    OPPORTUNITIES_SITE("/opportunities/{site}", Object.class),
    OPPORTUNITIES_SITE_ID("/opportunities/{site}/{id}", Object.class),

    // Services
    SERVICES_SITE("/servies/{site}", Object.class),

    // Weather
    WEATHER_CURRENT("/weather/current", Object.class),

    // Terms
    TERMS_LIST("/terms/list", Object.class),
    TERMS_TERM_COURSES("/terms/{term}/courses", Course.class),
    TERMS_TERM_EXAMSCHEDULE("/terms/{term}/examschedule", Object.class),
    TERMS_TERM_SUBJECT_SCHEDULE("/terms/{term}/{subject}/schedule", Object.class),
    TERMS_TERM_SUBJECT_CATALOGNUMBER_SCHEDULE("/terms/{term}/{subject}/{catalog_number}/schedule", Object.class),
    TERMS_TERM_ENROLLMENT("/terms/{term}/enrollment", Object.class),
    TERMS_TERM_SUBJECT_ENROLLMENT("/terms/{term}/{subject}/enrollment", Object.class),
    TERMS_TERM_INFOSESSIONS("/terms/{term}/infosessions", Object.class),

    // Resources
    RESOURCES_TUTORS("/resources/tutors", Object.class),
    RESOURCES_PRINTERS("/resources/printers", Object.class),
    RESOURCES_INFOSESSIONS("/resources/infosessions", Object.class),
    RESOURCES_GOOSEWATCH("/resources/goosewatch", Object.class),
    RESOURCES_SUNSHINELIST("/resources/sunshinelist", Object.class),

    // Definitions and Codes
    CODES_UNITS("/codes/units", Unit.class),
    CODES_TERMS("/codes/terms", Term.class),
    CODES_GROUPS("/codes/groups", Object.class),
    CODES_SUBJETS("/codes/subjects", Object.class),
    CODES_INSTRUCTIONS("/codes/instructions", Object.class),

    // Building
    BUILDINGS_LIST("/buildings/list", Object.class),
    BUILDINGS_BUILDINGCODE("/buildings/{building_code}", Object.class),
    BUILDINGS_BUILDING_ROOM_COURSES("/buildings/{building}/{room}/courses", Object.class),
    BUILDINGS_BUILDINGCODE_ACCESSPOINTS("/building/{building_code}/accesspoints", Object.class),
    BUILDINGS_BUILDINGCODE_VENDINGMACHINES("/buildings/{building_code}/vendingmachines", Object.class),

    // Points of Interest
    POI_ATMS("/poi/atms", Object.class),
    POI_GREYHOUND("/poi/greyhound", Object.class),
    POI_HELPLINES("/poi/helplines", Object.class),
    POI_LIBRARIES("/poi/libraries", Object.class),
    POI_PHOTOSPHERES("/poi/photospheres", Object.class),
    POI_DEFIBRILLATORS("/poi/defibrillators", Object.class),
    POI_CONSTRUCTIONSITES("/poi/constructionsites", Object.class),
    POI_ACCESSIBLEENTRANCES("/poi/accessibleentrances", Object.class),
    POI_VISITORINFORMATION("/poi/visitorinformation", Object.class),

    // Parking
    PARKING_WATPARK("/parking/watpark", Object.class),
    PARKING_LOTS_METER("/parking/lots/meter", Object.class),
    PARKING_LOTS_PERMIT("/parking/lots/permit", Object.class),
    PARKING_LOTS_VISITOR("/parking/lots/visitor", Object.class),
    PARKING_LOTS_SHORTTERM("/parking/lots/shortterm", Object.class),
    PARKING_LOTS_ACCESSIBLE("/parking/lots/accessible", Object.class),
    PARKING_LOTS_MOTORCYCLE("/parking/lots/motorcycle", Object.class),

    // Transit
    TRANSIT_GRT("/transit/grt", Object.class),
    TRANSIT_GRT_STOPS("/transit/grt/stops", Object.class),

    // People Directory Search
    DIRECTORY_USERID("/directory/{user_id}", Object.class),

    // API
    API_USAGE("/api/usage", Object.class),
    API_SERVICES("/api/services", Object.class),
    API_METHODS("/api/methods", Object.class),
    API_VERSIONS("/api/versions", Object.class),
    API_CHANGELOGS("/api/changelogs", Object.class);

    private static final Map<Class<?>, Endpoint> defaultEndpoints = new HashMap<Class<?>, Endpoint>() {{
        put(Course.class, COURSES_SUBJECT_CATALOGNUMBER);
    }};

    private static final String BASE_URL = "https://api.uwaterloo.ca/v2";
    private final String url;
    final Class c;

    Endpoint(String uri, Class c) {
        this.url = BASE_URL + uri + ".json";
        this.c = c;
    }

    Object getData(Object[] args, String key) {

        String authenticatedUrl = fillArguments(args) + "?key=" + key;
        Object json = getJson(authenticatedUrl).getJsonObject("data");



        try {
            Constructor<?> constructor = this.c.getConstructor(JsonObject.class);

            if(json instanceof JsonArray) {
//                List<Object> objects = new ArrayList<>();
//
//                for(Object in : (JsonArray) json) {
//                    objects.add(constructor.newInstance((JsonArray) in));
//                }
//
//                return objects;
                throw new RuntimeException("Shouldn't be here");
            }

            return constructor.newInstance(json);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Shouldn't be here!");
    }

    private String fillArguments(Object... args) {
        String filledUrl = this.url;
        Pattern p = Pattern.compile("\\{.*?\\}");
        Matcher m = p.matcher(this.url);

        int i;
        for (i = 0; m.find(); i++) {
            filledUrl = filledUrl.replaceFirst("\\{.*?\\}", args[i].toString());
        }

        if (i < args.length) {
            throw new IllegalArgumentException();
        }

        return filledUrl;

    }
}
