package UWaterloo;


import java.util.ArrayList;
import java.util.List;

public class Schedule extends Course {

    private int classNumber;
    private String section;
    private String campus;
    private int associatedClass;
    private String relatedComponent1;
    private String relatedComponent2;
    private int enrollmentCapacity;
    private int enrollmentTotal;
    private int waitingCapacity;
    private int waitingTotal;
    private String topic;
    private ArrayList<Reserves> reserves;
    private ArrayList<Classes> classes;
    private ArrayList<String> heldWith;
    private int term;
    private String lastUpdated;

    public Schedule(){}

    public int getClassNumber() {
        return classNumber;
    }

    protected void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getSection() {
        return section;
    }

    protected void setSection(String section) {
        this.section = section;
    }

    public String getCampus() {
        return campus;
    }

    protected void setCampus(String campus) {
        this.campus = campus;
    }

    public int getAssociatedClass() {
        return associatedClass;
    }

    protected void setAssociatedClass(Integer associatedClass) {
        this.associatedClass = associatedClass;
    }

    public String getRelatedComponent1() {
        return relatedComponent1;
    }

    public List<Reserves> getReserves() {
        return reserves;
    }

    protected void setReserves(ArrayList<Reserves> reserves) {
        this.reserves = reserves;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    protected void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    protected void setRelatedComponent1(String relatedComponent1) {
        this.relatedComponent1 = relatedComponent1;
    }

    public String getRelatedComponent2() {
        return relatedComponent2;
    }

    protected void setRelatedComponent2(String relatedComponent2) {
        this.relatedComponent2 = relatedComponent2;
    }

    public int getEnrollmentCapacity() {
        return enrollmentCapacity;
    }

    protected void setEnrollmentCapacity(Integer enrollmentCapacity) {
        this.enrollmentCapacity = enrollmentCapacity;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    protected void setEnrollmentTotal(Integer enrollmentTotal) {
        this.enrollmentTotal = enrollmentTotal;
    }

    public int getWaitingCapacity() {
        return waitingCapacity;
    }

    protected void setWaitingCapacity(Integer waitingCapacity) {
        this.waitingCapacity = waitingCapacity;
    }

    public int getWaitingTotal() {
        return waitingTotal;
    }

    protected void setWaitingTotal(Integer waitingTotal) {
        this.waitingTotal = waitingTotal;
    }

    public String getTopic() {
        return topic;
    }

    protected void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getHeldWith() {
        return heldWith;
    }

    protected void setHeldWith(ArrayList<String> heldWith) {
        this.heldWith = heldWith;
    }

    public int getTerm() {
        return term;
    }

    protected void setTerm(Integer term) {
        this.term = term;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    protected void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public class Reserves {
        private String reserveGroup;
        private int enrollmentCapacity;
        private int enrollmentTotal;

        public Reserves(){}

        public String getReserveGroup() {
            return reserveGroup;
        }

        protected void setReserveGroup(String reserveGroup) {
            this.reserveGroup = reserveGroup;
        }

        public int getEnrollmentCapacity() {
            return enrollmentCapacity;
        }

        protected void setEnrollmentCapacity(Integer enrollmentCapacity) {
            this.enrollmentCapacity = enrollmentCapacity;
        }

        public int getEnrollmentTotal() {
            return enrollmentTotal;
        }

        protected void setEnrollmentTotal(Integer enrollmentTotal) {
            this.enrollmentTotal = enrollmentTotal;
        }
    }

    public class Classes {

        private Date date;
        private Location location;
        private ArrayList<String> instructors;

        Classes(){}

        public Date getDate() {
            return date;
        }

        protected void setDate(Date date) {
            this.date = date;
        }

        public Location getLocation() {
            return location;
        }

        protected void setLocation(Location location) {
            this.location = location;
        }

        public List<String> getInstructors() {
            return instructors;
        }

        protected void setInstructors(ArrayList<String> instructors) {
            this.instructors = instructors;
        }

        public class Date {
            private String startTime;
            private String endTime;
            private String weekdays;
            private String startDate;
            private String endDate;
            private boolean isTba;
            private boolean isCancelled;
            private boolean isClosed;

            Date(){}

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

            public String getWeekdays() {
                return weekdays;
            }

            protected void setWeekdays(String weekdays) {
                this.weekdays = weekdays;
            }

            public String getStartDate() {
                return startDate;
            }

            protected void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            protected void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public boolean isTba() {
                return isTba;
            }

            protected void setIsTba(Boolean tba) {
                isTba = tba;
            }

            public boolean isCancelled() {
                return isCancelled;
            }

            protected void setIsCancelled(Boolean cancelled) {
                isCancelled = cancelled;
            }

            public boolean isClosed() {
                return isClosed;
            }

            protected void setIsClosed(Boolean closed) {
                isClosed = closed;
            }
        }

        public class Location {
            private String building;
            private String room;

            Location(){}

            public String getBuilding() {
                return building;
            }

            protected void setBuilding(String building) {
                this.building = building;
            }

            public String getRoom() {
                return room;
            }

            protected void setRoom(String room) {
                this.room = room;
            }
        }
    }
}
