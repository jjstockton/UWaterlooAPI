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
    WEATHER_CURRENT("/weather/current");


    final String uri;
    Endpoints(String uri) {
        this.uri = uri;
    }

}
