package UWaterloo;

public class Course {

    public String subject;
    public String number;
    public double units;
    public String name;
    public int courseId;

    public Course(String subject, String number, double units, String name){

        this.subject = subject;
        this.number = number;
        this.units = units;
        this.name = name;

    }

    public Course(String subject, String number, String name, int course_id){

        this.subject = subject;
        this.number = number;
        this.name = name;
        this.courseId = course_id;

    }



}
