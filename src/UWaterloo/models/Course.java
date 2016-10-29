package UWaterloo.models;

import UWaterloo.internal.json.JsonObject;

import java.util.List;

public class Course extends UWaterlooObject {

    public Course(JsonObject json) {
        super(json);
    }

    public String getCourseId() {
        return get("course_id", String.class);
    }

    public String getSubject() {
        return get("subject", String.class);
    }

    public String getCatalogNumber() {
        return getString("catalog_number");
    }

    public String getTitle() {
        return getString("title");
    }

    public double getUnits() {
        return getDouble("units");
    }

    public String getDescription() {
        return getString("description");
    }

    public List<String> getInstructions() {
        return getList("instructions", String.class);
    }

    public String getPrerequisites() {
        return getString("prerequisites");
    }

    public String getCorequisites() {
        return getString("corequisites");
    }

    public String getCrosslistings() {
        return getString("cross_listings");
    }

    public List<String> getTermsOffered() {
        return getList("terms_offered", String.class);
    }

    public String getNotes() {
        return getString("notes");
    }

    public boolean isNeedsDepartmentConsent() {
        return getBoolean("needs_department_consent");
    }

    public boolean isNeedsInstructorConsent() {
        return getBoolean("needs_instructor_consent");
    }

    public String getAntirequisites() {
        return getString("antirequisites");
    }

    public Offerings getOfferings() {
        return new Offerings(getJsonObject("offerings"));
    }

    public List<String> getExtra() {
        return getList("extra", String.class);
    }

    public String getCalendarYear() {
        return getString("calendar_year");
    }

    public String getUrl() {
        return getString("url");
    }

    public String getAcademicLevel() {
        return getString("academic_level");
    }

    public Schedule getSchedule() {
        return get("schedule", Schedule.class);
    }


    public class Offerings extends UWaterlooObject {

        public Offerings(JsonObject data) {
            super(data);
        }

        public boolean isOnline() {
            return getBoolean("online");
        }

        public boolean isOnlineOnly() {
            return getBoolean("online_only");
        }

        public boolean isStJerome() {
            return getBoolean("st_jerome");
        }

        public boolean isStJeromeOnly() {
            return getBoolean("st_jerome_only");
        }

        public boolean isRenison() {
            return getBoolean("renison");
        }

        public boolean isRenisonOnly() {
            return getBoolean("renison_only");
        }

        public boolean isConradGrebel() {
            return getBoolean("conrad_grebel");
        }

        public boolean isConradGrebelOnly() {
            return getBoolean("conrad_grebel_only");
        }
    }

    public class Schedule extends UWaterlooObject {

        public Schedule(JsonObject json) {
            super(json);
        }

        public int getClassNumber() {
            return get("class_number", Integer.class);
        }

        public String getSection() {
            return get("section", String.class);
        }

        public String getCampus() {
            return get("campus", String.class);
        }

        public int getAssociatedClass() {
            return get("associated_class", Integer.class);
        }

        public String getRelatedComponent1() {
            return get("related_component_1", String.class);
        }

        public List<Reserves> getReserves() {
            return getList("campus", Reserves.class);
        }

        public List<Classes> getClasses() {
            return getList("campus", Classes.class);
        }

        public String getRelatedComponent2() {
            return get("related_component_2", String.class);
        }

        public int getEnrollmentCapacity() {
            return get("enrollment_capacity", Integer.class);
        }

        public int getEnrollmentTotal() {
            return get("enrollmentTotal", Integer.class);
        }

        public int getWaitingCapacity() {
            return get("waiting_capacity", Integer.class);
        }

        public int getWaitingTotal() {
            return get("waiting_total", Integer.class);
        }

        public String getTopic() {
            return get("topic", String.class);
        }

        public List<String> getHeldWith() {
            return getList("campus", String.class);
        }

        public int getTerm() {
            return get("term", Integer.class);
        }

        public String getLastUpdated() {
            return get("last_updated", String.class);
        }

        public class Reserves extends UWaterlooObject {
            private String reserveGroup;
            private int enrollmentCapacity;
            private int enrollmentTotal;

            public Reserves(JsonObject data) {
                super(data);
            }

            public String getReserveGroup() {
                return get("reserve_group", String.class);
            }

            public int getEnrollmentCapacity() {
                return get("enrollment_capacity", Integer.class);
            }

            public int getEnrollmentTotal() {
                return get("enrollment_total", Integer.class);
            }
        }

        public class Classes extends UWaterlooObject {

            public Classes(JsonObject data) {
                super(data);
            }

            public Date getDate() {
                return get("date", Date.class);
            }

            public Location getLocation() {
                return get("location", Location.class);
            }

            public List<String> getInstructors() {
                return getList("instructors", String.class);
            }

            public class Date extends UWaterlooObject {

                public Date(JsonObject data) {
                    super(data);
                }

                public String getStartTime() {
                    return get("start_time", String.class);
                }

                public String getEndTime() {
                    return get("end_time", String.class);
                }


                public String getWeekdays() {
                    return get("weekdays", String.class);
                }

                public String getStartDate() {
                    return get("start_date", String.class);
                }

                public String getEndDate() {
                    return get("end_date", String.class);
                }

                public boolean isTba() {
                    return get("is_tba", Boolean.class);
                }

                public boolean isCancelled() {
                    return get("is_cancelled", Boolean.class);
                }

                public boolean isClosed() {
                    return get("is_closed", Boolean.class);
                }
            }

            public class Location extends UWaterlooObject {

                public Location(JsonObject data) {
                    super(data);
                }

                public String getBuilding() {
                    return get("campus", String.class);
                }

                public String getRoom() {
                    return get("room", String.class);
                }

            }
        }
    }
}

