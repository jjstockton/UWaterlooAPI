package UWaterloo;

public enum Endpoints {

    // Food Services
    FOODSERVICES_MENU("/foodservices/menu"),
    FOODSERVICES_NOTES("/foodservices/notes"),
    FOODSERVICES_DIETS("/foodservices/diets"),
    FOODSERVICES_OUTLETS("/foodservices/outlets"),
    FOODSERVICES_LOCATIONS("/foodservices/locations"),
    FOODSERVICES_WATCARD("/foodservices/watcard"),
    FOODSERVICES_ANNOUNCEMENTS("/foodservices/announcements"),
    FOODSERVICES_PRODUCTS_PRODUCTID("/foodservices/products/product_id"),
    FOODSERVICES_YEAR_WEEK_MENU("/foodservices/{year}/{week}/menu"),
    FOODSERVICES_YEAR_WEEK_NOTES("/foodservices/{year}/{week}/notes"),
    FOODSERVICES_YEAR_WEEK_ANNOUNCEMENTS("/foodservices/{year}/{week}/announcements"),

    //FEDS
    FEDS_EVENTS("/feds/events"),
    FEDS_EVENTS_ID("/feds/events/{id}"),
    FEDS_LOCATIONS("/feds/locations"),

    // Course
    COURSES("/courses"),
    COURSES_SUBJECT("/courses/{subject}"),
    COURSES_COURSEID("/courses/{course_id}"),
    COURSES_CLASSNUMBER_SCHEDULE("/courses/{class_number}/schedule"),
    COURSES_SUBJECT_CATALOGNUMBER("/courses/{subject}/{catalog_number}"),
    COURSES_SUBJECT_CATALOGNUMBER_SCHEDULE("/courses/{subject}/{catalog_number}/schedule"),
    COURSES_SUBJECT_CATALOGNUMBER_PREREQUISITES("/courses/{subject}/{catalog_number}/prerequisites"),
    COURSES_SUBJECT_CATALOGNUMBER_EXAMSCHEDULE("/courses/{subject}/{catalog_number}/examschedule"),

    // Awards/Scholarships
    AWARDS_GRADUATE("/awards/graduate"),
    AWARDS_UNDERGRADUATE("/awards/undergraduate"),

    // Events
    EVENTS("/events"),
    EVENTS_SITE("/events/{site}"),
    EVENTS_SITE_ID("/events/{site}/{id}"),
    EVENTS_HOLIDAYS("/events/holidays"),

    // Blogs
    BLOGS_SITE("/blogs/{site}"),
    BLOGS_SITE_ID("/blogs/{site}/{id}"),

    // News
    NEWS("/news"),
    NEWS_SITE("/news/{site}"),
    NEWS_SITE_ID("/news/{site}/{id}"),

    // Opportunities/Jobs
    OPPORTUNITIES("/opportunities"),
    OPPORTUNITIES_SITE("/opportunities/{site}"),
    OPPORTUNITIES_SITE_ID("/opportunities/{site}/{id}"),

    // Services
    SERVICES_SITE("/servies/{site}"),

    // Weather
    WEATHER_CURRENT("/weather/current"),

    // Terms
    TERMS_LIST("/terms/list"),
    TERMS_TERM_COURSES("/terms/{term}/courses"),
    TERMS_TERM_EXAMSCHEDULE("/terms/{term}/examschedule"),
    TERMS_TERM_SUBJECT_SCHEDULE("/terms/{term}/{subject}/schedule"),
    TERMS_TERM_SUBJECT_CATALOGNUMBER_SCHEDULE("/terms/{term}/{subject}/{catalog_number}/schedule"),
    TERMS_TERM_ENROLLMENT("/terms/{term}/enrollment"),
    TERMS_TERM_SUBJECT_ENROLLMENT("/terms/{term}/{subject}/enrollment"),
    TERMS_TERM_INFOSESSIONS("/terms/{term}/infosessions"),

    // Resources
    RESOURCES_TUTORS("/resources/tutors"),
    RESOURCES_PRINTERS("/resources/printers"),
    RESOURCES_INFOSESSIONS("/resources/infosessions"),
    RESOURCES_GOOSEWATCH("/resources/goosewatch"),
    RESOURCES_SUNSHINELIST("/resources/sunshinelist"),

    // Definitions and Codes
    CODES_UNITS("/codes/units"),
    CODES_TERMS("/codes/terms"),
    CODES_GROUPS("/codes/groups"),
    CODES_SUBJETS("/codes/subjects"),
    CODES_INSTRUCTIONS("/codes/instructions"),

    // Building
    BUILDINGS_LIST("/buildings/list"),
    BUILDINGS_BUILDINGCODE("/buildings/{building_code}"),
    BUILDINGS_BUILDING_ROOM_COURSES("/buildings/{building}/{room}/courses"),
    BUILDINGS_BUILDINGCODE_ACCESSPOINTS("/building/{building_code}/accesspoints"),
    BUILDINGS_BUILDINGCODE_VENDINGMACHINES("/buildings/{building_code}/vendingmachines"),

    // Points of Interest
    POI_ATMS("/poi/atms"),
    POI_GREYHOUND("/poi/greyhound"),
    POI_HELPLINES("/poi/helplines"),
    POI_LIBRARIES("/poi/libraries"),
    POI_PHOTOSPHERES("/poi/photospheres"),
    POI_DEFIBRILLATORS("/poi/defibrillators"),
    POI_CONSTRUCTIONSITES("/poi/constructionsites"),
    POI_ACCESSIBLEENTRANCES("/poi/accessibleentrances"),
    POI_VISITORINFORMATION("/poi/visitorinformation"),

    // Parking
    PARKING_WATPARK("/parking/watpark"),
    PARKING_LOTS_METER("/parking/lots/meter"),
    PARKING_LOTS_PERMIT("/parking/lots/permit"),
    PARKING_LOTS_VISITOR("/parking/lots/visitor"),
    PARKING_LOTS_SHORTTERM("/parking/lots/shortterm"),
    PARKING_LOTS_ACCESSIBLE("/parking/lots/accessible"),
    PARKING_LOTS_MOTORCYCLE("/parking/lots/motorcycle"),

    // Transit
    TRANSIT_GRT("/transit/grt"),
    TRANSIT_GRT_STOPS("/transit/grt/stops"),

    // People Directory Search
    DIRECTORY_USERID("/directory/{user_id}"),

    // API
    API_USAGE("/api/usage"),
    API_SERVICES("/api/services"),
    API_METHODS("/api/methods"),
    API_VERSIONS("/api/versions"),
    API_CHANGELOGS("/api/changelogs");

    final String uri;
    Endpoints(String uri) {
        this.uri = uri;
    }

}
