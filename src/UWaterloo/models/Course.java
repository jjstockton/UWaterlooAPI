package UWaterloo.models;

import org.json.JSONObject;

public class Course extends UWaterlooObject {

    private JSONObject data;

    public Course(JSONObject data) {
        this.data = data;
    }

    public Course(String courseId) {
    }

    public String getCourseId() {
        return data.getString("course_id");
    }

    public String getSubject() {
        return data.getString("subject");
    }

    public String getCatalogNumber() {
        return data.getString("catalog_number");
    }

    public String getTitle() {
        return data.getString("title");
    }

    public double getUnits() {
        return data.getDouble("units");
    }

    public String getDescription() {
        return data.getString("description");
    }

//    public List<String> getInstructions() {
//        return data.getString("catalog_number");
//    }

    public String getPrerequisites() {
        return data.getString("prerequisites");
    }

    public String getCorequisites() {
        return data.getString("corequisites");
    }

    public String getCrosslistings() {
        return data.getString("cross_listings");
    }

//    public List<String> getTermsOffered() {
//        return termsOffered;
//    }

    public String getNotes() {
        return data.getString("notes");
    }

    public boolean isNeedsDepartmentConsent() {
        return data.getBoolean("needs_department_consent");
    }

    public boolean isNeedsInstructorConsent() {
        return data.getBoolean("needs_instructor_consent");
    }

    public String getAntirequisites() {
        return data.getString("antirequisites");
    }

    public Offerings getOfferings() {
        return new Offerings(data.getJSONObject("offerings"));
    }

//    public List<String> getExtra() {
//        return extra;
//    }

    public String getCalendarYear() {
        return data.getString("calendar_year");
    }

    public String getUrl() {
        return data.getString("url");
    }

    public String getAcademicLevel() {
        return data.getString("academic_level");
    }


    public class Offerings {

        private JSONObject data;
        public Offerings(JSONObject data) {
            this.data = data;
        }

        public boolean isOnline() {
            return data.getBoolean("online");
        }

        public boolean isOnlineOnly() {
            return data.getBoolean("online_only");
        }

        public boolean isStJerome() {
            return data.getBoolean("st_jerome");
        }

        public boolean isStJeromeOnly() {
            return data.getBoolean("st_jerome_only");
        }

        public boolean isRenison() {
            return data.getBoolean("renison");
        }

        public boolean isRenisonOnly() {
            return data.getBoolean("renison_only");
        }

        public boolean isConradGrebel() {
            return data.getBoolean("conrad_grebel");
        }

        public boolean isConradGrebelOnly() {
            return data.getBoolean("conrad_grebel_only");
        }

    }
}

