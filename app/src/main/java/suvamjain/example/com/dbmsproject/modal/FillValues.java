package suvamjain.example.com.dbmsproject.modal;

/**
 * Created by SUVAM JAIN on 20-04-2017.
 */

public class FillValues {

    private String course_name;
    private String course_code;
    private String s_time;
    private String e_time;
    private String day;
    private String slot;
    private String faculty;
    private String cabin;
    private String room;

   
    public String getSlot() { return slot; }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return s_time;
    }

    public void setStartTime(String s_time) {
        this.s_time = s_time;
    }

    public String getEndTime() {
        return e_time;
    }

    public void setEndTime(String e_time) {
        this.e_time = e_time;
    }

    public String getCourseName() {
        return course_name;
    }

    public void setCourseName(String course_name) { this.course_name = course_name; }

    public String getCourseCode() {
        return course_code;
    }

    public void setCourseCode(String course_code) { this.course_code = course_code; }

    public String getFaculty() { return faculty; }

    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getRoom(){ return room;}

    public void setRoom(String room) { this.room = room; }

    public String getCabin(){ return cabin;}

    public void setCabin(String  cabin) { this.cabin = cabin; }








}
