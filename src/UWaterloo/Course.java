package UWaterloo;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseId;
    private String subject;
    private String catalogNumber;
    private String title;
    private double units;
    private String description;
    private ArrayList<String> instructions;
    private String prerequisites;
    private String antirequisites;
    private String corequisites;
    private String crosslistings;
    private ArrayList<String> termsOffered;
    private String notes;
    private Offerings offerings;
    private boolean needsDepartmentConsent;
    private boolean needsInstructorConsent;
    private ArrayList<String> extra;
    private String calendarYear;
    private String url;
    private String academicLevel;

    protected Course() {}

    public Course(String subject, String catalogNumber) {
        this.subject = subject;
        this.catalogNumber = catalogNumber;
    }

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    protected void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSubject() {
        return subject;
    }

    protected void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    protected void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public double getUnits() {
        return units;
    }

    protected void setUnits(Double units) {
        this.units = units;
    }

    protected void setUnits(Integer units) {
        this.units = (double) units;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    protected void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    protected void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getCorequisites() {
        return corequisites;
    }

    protected void setCorequisites(String corequisites) {
        this.corequisites = corequisites;
    }

    public String getCrosslistings() {
        return crosslistings;
    }

    protected void setCrosslistings(String crosslistings) {
        this.crosslistings = crosslistings;
    }

    public List<String> getTermsOffered() {
        return termsOffered;
    }

    protected void setTermsOffered(ArrayList<String> termsOffered) {
        this.termsOffered = termsOffered;
    }

    public String getNotes() {
        return notes;
    }

    protected void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isNeedsDepartmentConsent() {
        return needsDepartmentConsent;
    }

    protected void setNeedsDepartmentConsent(Boolean needsDepartmentConsent) {
        this.needsDepartmentConsent = needsDepartmentConsent;
    }

    public boolean isNeedsInstructorConsent() {
        return needsInstructorConsent;
    }

    public String getAntirequisites() {
        return antirequisites;
    }

    public void setAntirequisites(String antirequisites) {
        this.antirequisites = antirequisites;
    }

    public Offerings getOfferings() {
        return offerings;
    }

    public void setOfferings(Offerings offerings) {
        this.offerings = offerings;
    }

    protected void setNeedsInstructorConsent(Boolean needsInstructorConsent) {
        this.needsInstructorConsent = needsInstructorConsent;
    }

    public List<String> getExtra() {
        return extra;
    }

    protected void setExtra(ArrayList<String> extra) {
        this.extra = extra;
    }

    public String getCalendarYear() {
        return calendarYear;
    }

    protected void setCalendarYear(String calendarYear) {
        this.calendarYear = calendarYear;
    }

    public String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    protected void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }


    public class Offerings {

        private boolean online;
        private boolean onlineOnly;
        private boolean stJerome;
        private boolean stJeromeOnly;
        private boolean renison;
        private boolean renisonOnly;
        private boolean conradGrebel;
        private boolean conradGrebelOnly;

        Offerings() {}

        public boolean isOnline() {
            return online;
        }

        protected void setOnline(Boolean online) {
            this.online = online;
        }

        public boolean isOnlineOnly() {
            return onlineOnly;
        }

        protected void setOnlineOnly(Boolean onlineOnly) {
            this.onlineOnly = onlineOnly;
        }

        public boolean isStJerome() {
            return stJerome;
        }

        protected void setStJerome(Boolean stJerome) {
            this.stJerome = stJerome;
        }

        public boolean isStJeromeOnly() {
            return stJeromeOnly;
        }

        protected void setStJeromeOnly(Boolean stJeromeOnly) {
            this.stJeromeOnly = stJeromeOnly;
        }

        public boolean isRenison() {
            return renison;
        }

        protected void setRenison(Boolean renison) {
            this.renison = renison;
        }

        public boolean isRenisonOnly() {
            return renisonOnly;
        }

        protected void setRenisonOnly(Boolean renisonOnly) {
            this.renisonOnly = renisonOnly;
        }

        public boolean isConradGrebel() {
            return conradGrebel;
        }

        protected void setConradGrebel(Boolean conradGrebel) {
            this.conradGrebel = conradGrebel;
        }

        public boolean isConradGrebelOnly() {
            return conradGrebelOnly;
        }

        protected void setConradGrebelOnly(Boolean conradGrebelOnly) {
            this.conradGrebelOnly = conradGrebelOnly;
        }

    }
}

