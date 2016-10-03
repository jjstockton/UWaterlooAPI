package UWaterloo.models;

import java.util.List;

public class ExamSchedule {

    private String course;
    private List<Sections> sections;

    public String getCourse() {
        return course;
    }

    protected void setCourse(String course) {
        this.course = course;
    }

    public List<Sections> getSections() {
        return sections;
    }

    protected void setSections(List<Sections> sections) {
        this.sections = sections;
    }

    public class Sections {
        private String section;
        private String day;
        private String date;
        private String startTime;
        private String endTime;
        private String location;
        private String notes;

        Sections() {}

        public String getSection() {
            return section;
        }

        protected void setSection(String section) {
            this.section = section;
        }

        public String getDay() {
            return day;
        }

        protected void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        protected void setDate(String date) {
            this.date = date;
        }

        public String getStartTime() {
            return startTime;
        }

        protected void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        protected void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getLocation() {
            return location;
        }

        protected void setLocation(String location) {
            this.location = location;
        }

        public String getNotes() {
            return notes;
        }

        protected void setNotes(String notes) {
            this.notes = notes;
        }
    }
}
