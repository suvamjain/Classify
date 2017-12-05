package suvamjain.example.com.dbmsproject.sql;

/**
 * Created by SUVAM JAIN on 29-03-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import suvamjain.example.com.dbmsproject.modal.FacultyInfo;
import suvamjain.example.com.dbmsproject.modal.FillValues;
import suvamjain.example.com.dbmsproject.modal.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    /**
     * Constructor
     *
     * @param user_email is used everywhere in place of Regd.No.
     */
    private static final String TABLE_USER = "user";
    private static final String TABLE_FACULTY = "faculty";
    private static final String TABLE_SLOTS = "slots";
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_TIMETABLE = "timetable";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_SECURITY = "user_security";
    private static final String COLUMN_USER_DOB = "user_dob";
    private static final String COLUMN_USER_SEX = "user_sex";
    private static final String COLUMN_USER_YOA = "user_yoa";
    private static final String COLUMN_USER_SEMESTER = "user_semester";

    //private static final String COLUMN_COURSE_ID = "course_id";
    private static final String COLUMN_COURSE_NAME = "course_name";
    private static final String COLUMN_COURSE_CODE = "course_code";

    //private static final String COLUMN_FACULTY_ID = "faculty_id";
    private static final String COLUMN_FACULTY_NAME = "faculty_name";
    private static final String COLUMN_FACULTY_COURSE = "faculty_course";
    private static final String COLUMN_FACULTY_CABIN = "faculty_cabin";

    //private static final String COLUMN_SLOT_ID = "slot_id";
    private static final String COLUMN_SLOT_NAME = "slot_name";
    private static final String COLUMN_SLOT_DAY = "day";
    private static final String COLUMN_SLOT_START_TIME = "s_time";
    private static final String COLUMN_SLOT_END_TIME = "e_time";

    //private static final String COLUMN_TT_ID = "tt_id";
    private static final String COLUMN_TT_SLOT = "tt_slot";
    private static final String COLUMN_TT_COURSE_CODE = "tt_course_code";
    private static final String COLUMN_TT_ROOM = "room";
    private static final String COLUMN_TT_FACULTY = "tt_faculty";


    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_SECURITY + " TEXT,"
            + COLUMN_USER_DOB + " TEXT," + COLUMN_USER_SEX + " TEXT," + COLUMN_USER_YOA + " TEXT,"
            + COLUMN_USER_SEMESTER + " TEXT)";



    private String CREATE_SLOTS_TABLE = "CREATE TABLE " + TABLE_SLOTS + "("
            + COLUMN_SLOT_NAME + " TEXT," + COLUMN_SLOT_DAY + " TEXT,"
            + COLUMN_SLOT_START_TIME + " INTEGER," + COLUMN_SLOT_END_TIME + " INTEGER, PRIMARY KEY(" + COLUMN_SLOT_NAME + ","
            + COLUMN_SLOT_DAY + "," + COLUMN_SLOT_START_TIME + "," + COLUMN_SLOT_END_TIME + "))";


    private String CREATE_FACULTY_TABLE = "CREATE TABLE " + TABLE_FACULTY + "("
            + COLUMN_FACULTY_NAME + " TEXT PRIMARY KEY ," + COLUMN_FACULTY_CABIN + " TEXT)";

    private String CREATE_TIMETABLE_TABLE = "CREATE TABLE " + TABLE_TIMETABLE + "("
            + COLUMN_TT_SLOT + " TEXT," + COLUMN_TT_COURSE_CODE + " TEXT,"
            + COLUMN_TT_FACULTY + " TEXT," + COLUMN_TT_ROOM + " TEXT," + " PRIMARY KEY("
            + COLUMN_TT_SLOT + "," + COLUMN_TT_COURSE_CODE + "))";

    private String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
            + COLUMN_COURSE_CODE + " TEXT PRIMARY KEY," + COLUMN_COURSE_NAME + " TEXT)";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_SLOTS_TABLE = "DROP TABLE IF EXISTS " + TABLE_SLOTS;
    private String DROP_FACULTY_TABLE = "DROP TABLE IF EXISTS " + TABLE_FACULTY;
    private String DROP_COURSE_TABLE = "DROP TABLE IF EXISTS " + TABLE_COURSE;
    private String DROP_TIMETABLE_TABLE = "DROP TABLE IF EXISTS " + TABLE_TIMETABLE;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SLOTS_TABLE);
        db.execSQL(CREATE_FACULTY_TABLE);
        db.execSQL(CREATE_TIMETABLE_TABLE);
        db.execSQL(CREATE_COURSE_TABLE);

        // insert values into slots table

        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('A1','MONDAY',8,9)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('F1','MONDAY',9,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('D1','MONDAY',10,11)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TB1','MONDAY',11,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TG1','MONDAY',12,13)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('A2','MONDAY',14,15)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('F2','MONDAY',15,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('D2','MONDAY',16,17)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TB2','MONDAY',17,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TG2','MONDAY',18,19)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L1-L2','MONDAY',8,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L3-L4','MONDAY',10,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L5-L6','MONDAY',12,14)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L31-L32','MONDAY',14,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L33-L34','MONDAY',16,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L35-L36','MONDAY',18,20)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('B1','TUESDAY',8,9)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('G1','TUESDAY',9,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('E1','TUESDAY',10,11)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TC1','TUESDAY',11,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TAA1','TUESDAY',12,13)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('B2','TUESDAY',14,15)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('G2','TUESDAY',15,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('E2','TUESDAY',16,17)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TC2','TUESDAY',17,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TAA2','TUESDAY',18,19)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L7-L8','TUESDAY',8,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L9-L10','TUESDAY',10,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L11-L12','TUESDAY',12,14)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L37-L38','TUESDAY',14,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L39-L40','TUESDAY',16,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L41-L42','TUESDAY',18,20)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('C1','WEDNESDAY',8,9)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('A1','WEDNESDAY',9,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('F1','WEDNESDAY',10,11)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('C2','WEDNESDAY',14,15)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('A2','WEDNESDAY',15,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('F2','WEDNESDAY',16,17)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TD2','WEDNESDAY',17,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TBB2','WEDNESDAY',18,19)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L13-L14','WEDNESDAY',8,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L15-L16','WEDNESDAY',10,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L43-L44','WEDNESDAY',14,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L45-L46','WEDNESDAY',16,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L47-L48','WEDNESDAY',18,20)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('D1','THURSDAY',8,9)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('B1','THURSDAY',9,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('G1','THURSDAY',10,11)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TE1','THURSDAY',11,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TCC1','THURSDAY',12,13)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('D2','THURSDAY',14,15)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('B2','THURSDAY',15,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('G2','THURSDAY',16,17)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TE2','THURSDAY',17,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TCC2','THURSDAY',18,19)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L19-L20','THURSDAY',8,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L21-L22','THURSDAY',10,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L23-L24','THURSDAY',12,14)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L49-L50','THURSDAY',14,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L51-L52','THURSDAY',16,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L53-L54','THURSDAY',18,20)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('E1','FRIDAY',8,9)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('C1','FRIDAY',9,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TA1','FRIDAY',10,11)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TF1','FRIDAY',11,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TD1','FRIDAY',12,13)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('E2','FRIDAY',14,15)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('C2','FRIDAY',15,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TA2','FRIDAY',16,17)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TF2','FRIDAY',17,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('TDD2','FRIDAY',18,19)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L25-L26','FRIDAY',8,10)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L27-L28','FRIDAY',10,12)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L29-L30','FRIDAY',12,14)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L55-L56','FRIDAY',14,16)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L57-L58','FRIDAY',16,18)");
        db.execSQL(" insert into  " + TABLE_SLOTS + "   values('L59-L60','FRIDAY',18,20)");


        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CHY1001','CHEMISTRY')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('PHY1001','PHYSICS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CHY1002','EVS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('PHY1999','INNOVATIVE')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('MAT2001','STATISTICS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('MAT2002','ADDE')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE2004','DBMS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('STS2001','SOFT SKILLS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('HUM1021','ETHICS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE1002','OOPS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE1001','PROGRAMMING')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE1003','DLD')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('EEE1001','ELECTRICAL')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('ENG1011','ENGLISH')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('FRE1001','FRENCH')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('MAT1011','CALCULUS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('ECE1004','SIGNALS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('ECE1005','SENSORS')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE1004','NETWORKING')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE2001','CAO')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE2003','DSA')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE3001','SOFTWARE ENGG')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('CSE3002','IWP')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('MAT1014','DISCRETE MATH')");
        db.execSQL(" insert into  " + TABLE_COURSE + "   values('MGT1022','LEAN STARTUP')");

        // filling faculty table
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('NITHYANANDAM','AB1-404-10')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('KRISHNENDU B','AB1-ANX1-02')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('SANTOSH G','AB1-101-17')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('ATANU DUTTA','AB1-202-LAB')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('GEETHA S','AB1-205-LAB')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('KALIYAPPAN M','AB1-ANX1-09')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('PANKAJ SHUKLA','AB1-202-07')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('MANIKANDAN N','AB1-202-02')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('RAJIV G','AB1-101-12')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('NIRMAL THYAGU','CANT-RW-23')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('MOHANA N','AB1-202-03')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('SALEENA B','AB1-302-06')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('JAYANTA P','CANT-LW-04')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('NATHIYA N','CANT-LW-10')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('SAHRON SOPHIA','ADM-8-00')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('ALOK CHAUHAN','AB1-602-07')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('JENILA L.','AB1-402-01')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('SUBBULAKSHMI T','AB1-ANX5-06')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('RAJALAKSHMI R','CANT-LW-11')");
        db.execSQL(" insert into  " + TABLE_FACULTY + "   values('R BHARGAVI','NOT AVAILABLE')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_SLOTS_TABLE);
        db.execSQL(DROP_FACULTY_TABLE);
        db.execSQL(DROP_TIMETABLE_TABLE);
        db.execSQL(DROP_COURSE_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_SECURITY,user.getSecurity());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    /**
     * This method is to add user's timetable
     *
     * @param slot,code,room,faculty
     */
    public void addTimeTable(String slot, String code, String room,String faculty) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TT_SLOT, slot);
        values.put(COLUMN_TT_COURSE_CODE, code);
        values.put(COLUMN_TT_FACULTY, faculty);
        values.put(COLUMN_TT_ROOM, room);

        // Inserting Row
        db.insert(TABLE_TIMETABLE, null, values);
        db.close();
    }

    public Cursor getDetails()
    {   SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT * FROM " + TABLE_USER;
        Cursor res = db.rawQuery(query, null);
        return res;
    }


    public List<FillValues> getRemainingClasses(String day,int hour) {

        List<FillValues> usernextclass = new ArrayList<FillValues>();
        // select s_time,e_time,tt_slot,code,C_NAME,room,tt_faculty from (select * from timetable1),slots,NAME where tt_course_code = code and day
        //= 'MONDAY' and tt_slot = slot_name and (s_time >= 9 OR E_TIME>9)  order by s_time;


        String selectAllClasses = "SELECT "  + COLUMN_SLOT_START_TIME + " , "
                + COLUMN_SLOT_END_TIME + " , " + COLUMN_TT_SLOT + " , " + COLUMN_COURSE_CODE + " , "
                + COLUMN_COURSE_NAME + " , " + COLUMN_TT_FACULTY + " , " + COLUMN_TT_ROOM + " FROM (SELECT * FROM "
                + TABLE_TIMETABLE + " ), " + TABLE_SLOTS + " , " + TABLE_COURSE + " WHERE "
                + COLUMN_SLOT_NAME + " = " + COLUMN_TT_SLOT + " AND " + COLUMN_COURSE_CODE + " = " + COLUMN_TT_COURSE_CODE
                + " AND " + COLUMN_SLOT_DAY +  " = '" + day + "' AND (" + COLUMN_SLOT_START_TIME + " >= " + hour + " OR "
                + COLUMN_SLOT_END_TIME + " > " + hour + ") ORDER BY " + COLUMN_SLOT_START_TIME;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllClasses, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FillValues v = new FillValues();
                v.setStartTime(c.getString(c.getColumnIndex(COLUMN_SLOT_START_TIME)) + " 00 hrs");
                v.setEndTime(c.getString(c.getColumnIndex(COLUMN_SLOT_END_TIME)) + " 00 hrs");
                v.setSlot(c.getString(c.getColumnIndex(COLUMN_TT_SLOT)));
                v.setCourseCode(c.getString(c.getColumnIndex(COLUMN_COURSE_CODE)));
                v.setCourseName(c.getString(c.getColumnIndex(COLUMN_COURSE_NAME)));
                v.setFaculty(c.getString(c.getColumnIndex(COLUMN_TT_FACULTY)));
                v.setRoom(c.getString((c.getColumnIndex(COLUMN_TT_ROOM))));

                // adding to fillvalues list
                usernextclass.add(v);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        // return user list
        return usernextclass;
    }


    public List<FillValues> getAllClasses() {

        List<FillValues> userclass = new ArrayList<FillValues>();

        String selectAllClasses = "SELECT " + COLUMN_SLOT_DAY + " , " + COLUMN_SLOT_START_TIME + " , "
                + COLUMN_SLOT_END_TIME + " , " + COLUMN_TT_SLOT + " , " + COLUMN_TT_COURSE_CODE + " , "
                + COLUMN_COURSE_NAME + " , " + COLUMN_TT_FACULTY + " , " + COLUMN_TT_ROOM + " FROM (SELECT * FROM "
                + TABLE_TIMETABLE + " ), " + TABLE_SLOTS + " , " + TABLE_COURSE + " WHERE "
                + COLUMN_SLOT_NAME + " = " + COLUMN_TT_SLOT + " AND " + COLUMN_COURSE_CODE + " = " + COLUMN_TT_COURSE_CODE
                + " ORDER BY CASE WHEN "
                + COLUMN_SLOT_DAY + " = 'SUNDAY' THEN 1 WHEN " + COLUMN_SLOT_DAY + " = 'MONDAY' THEN 2 WHEN "
                + COLUMN_SLOT_DAY + " = 'TUESDAY' THEN 3 WHEN " + COLUMN_SLOT_DAY + " =  'WEDNESDAY' THEN 4  WHEN "
                + COLUMN_SLOT_DAY + " = 'THURSDAY' THEN 5 WHEN " + COLUMN_SLOT_DAY + " =  'FRIDAY' THEN 6 WHEN "
                + COLUMN_SLOT_DAY + " = 'SATURDAY' THEN 7 END ASC," + COLUMN_SLOT_START_TIME;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllClasses, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FillValues v = new FillValues();
                v.setDay(c.getString(c.getColumnIndex(COLUMN_SLOT_DAY)));
                v.setStartTime(c.getString(c.getColumnIndex(COLUMN_SLOT_START_TIME)) + " 00 hrs");
                v.setEndTime(c.getString(c.getColumnIndex(COLUMN_SLOT_END_TIME)) + " 00 hrs");
                v.setSlot(c.getString(c.getColumnIndex(COLUMN_TT_SLOT)));
                v.setCourseCode(c.getString(c.getColumnIndex(COLUMN_TT_COURSE_CODE)));
                v.setCourseName(c.getString(c.getColumnIndex(COLUMN_COURSE_NAME)));
                v.setFaculty(c.getString(c.getColumnIndex(COLUMN_TT_FACULTY)));
                v.setRoom(c.getString((c.getColumnIndex(COLUMN_TT_ROOM))));

                // adding to fillvalues list
                userclass.add(v);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        // return user list
        return userclass;
    }

    public List<FacultyInfo> getAllFacultyInfo() {
        List<FacultyInfo> facultyInfos = new ArrayList<FacultyInfo>();

        //select distinct tt_faculty,cabin,tt_course_code,c_name from timetable1,name,faculty2 where
        // tt_course_code = code and faculty_name = TT_faculty ORDER BY TT_COURSE_CODE;

        String FacultyInfo = "SELECT DISTINCT " + COLUMN_TT_FACULTY + " , " + COLUMN_FACULTY_CABIN + " , " + COLUMN_TT_COURSE_CODE + " , "
                 + COLUMN_COURSE_NAME + " FROM " + TABLE_TIMETABLE + " , " + TABLE_COURSE + " , " + TABLE_FACULTY + " WHERE "
                 + COLUMN_TT_COURSE_CODE +  " = " + COLUMN_COURSE_CODE + " AND " + COLUMN_FACULTY_NAME + " = " + COLUMN_TT_FACULTY
                 + " ORDER BY " + COLUMN_TT_COURSE_CODE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(FacultyInfo, null);
        // looping through all rows and adding to list

        if (c.moveToFirst()) {
            do {
                FacultyInfo v = new FacultyInfo();
                v.setCourseCode(c.getString(c.getColumnIndex(COLUMN_TT_COURSE_CODE)));
                v.setCourseName(c.getString(c.getColumnIndex(COLUMN_COURSE_NAME)));
                v.setFaculty(c.getString(c.getColumnIndex(COLUMN_TT_FACULTY)));
                v.setCabin(c.getString((c.getColumnIndex(COLUMN_FACULTY_CABIN))));

                // adding to facultyinfos list
                facultyInfos.add(v);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        // return user list
        return facultyInfos;

    }

    /**
     * This method to update user record
     *
     * @param
     */
    public void updateUser(int id,String name,String regd,String password,String security,String sex,String dob, String yoa,String sem) {
        //Toast.makeText(this,"Inside db value is \n " + name + "-" + dob + "-" +yoa +"-" + sem ,Toast.LENGTH_LONG).show();
        Log.e(TAG,"Inside db value is \n " + id + name + regd + "-" +password+ "-" +security+"-"+ dob + "-" +yoa +"-" + sem + "-" + sex );
        SQLiteDatabase db = this.getWritableDatabase();
        deleteUser(id);

        Log.e(TAG,"Items Deleted Now inserting");

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_EMAIL, regd);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_SECURITY,security);
        values.put(COLUMN_USER_SEX,sex);
        values.put(COLUMN_USER_DOB,dob);
        values.put(COLUMN_USER_YOA,yoa);
        values.put(COLUMN_USER_SEMESTER,sem);
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param
     */
    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    /**
     * This method is to delete user record
     *
     * @param Course
     */
    public void deleteCourse(String Course) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_TIMETABLE, COLUMN_TT_COURSE_CODE + " = ?",
                new String[]{Course});
        db.close();
    }

    /**
     * This method to check user already exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jainsuvam1@gmail.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user's reg no and password matches or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = '16BCE1138' AND user_password = 'mno';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    // Here is the extra portion for security hint

    public boolean checkSecurity(String email, String security) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_SECURITY + " = ?";

        // selection arguments
        String[] selectionArgs = {email, security};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = '16BCE1138' AND security = 'mno';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    // added by me(working)

    public Cursor search(String reg) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(TABLE_USER, null, COLUMN_USER_EMAIL + "=?", new String[]{reg}, null, null, null);
        return res;
    }

    /**
     * Getting all slots
     * returns list of slots
     */
    public List<String> getAllSlots(int x) {
        List<String> slots = new ArrayList<String>();
        String selectQuery;

        if (x == 0)
            // Select All Query (theory slots)
            selectQuery = "SELECT DISTINCT " + COLUMN_SLOT_NAME + " FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_NAME + " NOT LIKE 'L%' ORDER BY " + COLUMN_SLOT_NAME;
        else
            // Select All Query (lab slots)
            selectQuery = "SELECT DISTINCT " + COLUMN_SLOT_NAME + " FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_NAME + " LIKE 'L%' ORDER BY " + COLUMN_SLOT_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                slots.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning slots
        if (x == 0) {

            slots.add("A1+TA1");
            slots.add("B1+TB1");
            slots.add("C1+TC1");
            slots.add("D1+TD1");
            slots.add("E1+TE1");
            slots.add("F1+TF1");
            slots.add("G1+TG1");
            slots.add("A2+TA2");
            slots.add("B2+TB2");
            slots.add("C2+TC2");
            slots.add("D2+TD2");
            slots.add("E2+TE2");
            slots.add("F2+TF2");
            slots.add("G2+TG2");
            // add hint as last item
            slots.add("Select Theory Slot");
        }
        else
            // add hint as last item
            slots.add("Select Lab Slot");
        return slots;
    }

    public List<String> getAllCourseCodes(int x) {
        List<String> codes = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(x == 1)
        {   // show all the courses in the course list always , as for lab slots we need to register same course for many no of times.
            String selectQuery = "SELECT DISTINCT " + COLUMN_COURSE_CODE + " FROM " + TABLE_COURSE
                    + " ORDER BY " + COLUMN_COURSE_CODE;
            cursor = db.rawQuery(selectQuery, null);
        }
        if(x == 0)
        {   // FOR SHOWING UNREGISTERED COURSES IN ADD COURSE ACTIVITY for theory slots which may be registered for lab slots but
            // not yet registered for theory slots.
            // select code from name where code not in (select distinct tt_course_code from timetable1 WHERE tt_slot not like 'L%') order by code;
            String selectQuery = "SELECT " + COLUMN_COURSE_CODE + " FROM " + TABLE_COURSE
                    + " WHERE " + COLUMN_COURSE_CODE + " NOT IN (SELECT DISTINCT " + COLUMN_TT_COURSE_CODE + " FROM " + TABLE_TIMETABLE
                    + " WHERE " + COLUMN_TT_SLOT + " NOT LIKE 'L%')"
                    + " ORDER BY " + COLUMN_COURSE_CODE;
            cursor = db.rawQuery(selectQuery, null);
        }
        if(x == 2)
        {   // FOR SHOWING REGISTERED COURSES IN DELETE COURSE PROMPT WINDOW
            // select code from name where code not in (select distinct tt_course_code from timetable1) order by code;
            String selectQuery = "SELECT DISTINCT " + COLUMN_TT_COURSE_CODE + " FROM " + TABLE_TIMETABLE
                   + " ORDER BY " + COLUMN_TT_COURSE_CODE;
            cursor = db.rawQuery(selectQuery, null);
        }

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                codes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        // add hint as last item
        codes.add("Select Course Code");
        return codes;
    }

    public List<String> getAllFaculty() {
        List<String> faculty = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT " + COLUMN_FACULTY_NAME + " FROM " + TABLE_FACULTY
                + " ORDER BY " + COLUMN_FACULTY_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                faculty.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        // add hint as last item
        faculty.add("Select Faculty");
        return faculty;
    }

    public Cursor getAllClashingClass(String slot, int type) {

        String selectLabClashingClasses = "SELECT D," + COLUMN_SLOT_NAME
                + " FROM (SELECT " + COLUMN_SLOT_DAY + " AS D," + COLUMN_SLOT_NAME + " , "
                + COLUMN_SLOT_START_TIME + " , " + COLUMN_SLOT_END_TIME + " FROM " + TABLE_SLOTS + " WHERE "
                + COLUMN_SLOT_NAME + " LIKE 'L%' AND " + COLUMN_SLOT_NAME + " IN (SELECT " + COLUMN_TT_SLOT
                + " FROM " + TABLE_TIMETABLE + ") AND " + COLUMN_SLOT_DAY + " IN (SELECT " + COLUMN_SLOT_DAY
                + " FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_NAME + " = '" + slot + "')) WHERE "
                + COLUMN_SLOT_START_TIME + " = (SELECT " + COLUMN_SLOT_START_TIME + " FROM " + TABLE_SLOTS
                + " WHERE " + COLUMN_SLOT_NAME + " = '" + slot + "' AND " + COLUMN_SLOT_DAY + " = D) OR "
                + COLUMN_SLOT_END_TIME + " = ( SELECT " + COLUMN_SLOT_END_TIME + " FROM " + TABLE_SLOTS
                + " WHERE " + COLUMN_SLOT_NAME + " = '" + slot + "' AND DAY = D)";

        String selectTheoryClashingClasses = "SELECT " + COLUMN_SLOT_NAME
                + " FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_NAME + " IN (SELECT " + COLUMN_TT_SLOT
                + " FROM " + TABLE_TIMETABLE + ") AND (" + COLUMN_SLOT_START_TIME + " = (SELECT "
                + COLUMN_SLOT_START_TIME + " FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_NAME + " = '" + slot + "') OR "
                + COLUMN_SLOT_END_TIME + " = ( SELECT " + COLUMN_SLOT_END_TIME + " FROM " + TABLE_SLOTS
                + " WHERE " + COLUMN_SLOT_NAME + " = '" + slot + "')) AND DAY = (SELECT "
                + COLUMN_SLOT_DAY + " FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_NAME + " = '" + slot + "')";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;

        // if we r registering any theory class then we will check for lab clashing classes and vice versa.
        // N.B.- theory slot can't clash becoz as soon as any course is registerd for theory next time
        // it will not show that course code in theory slot and if we choose the same slot already registered
        // it will show error .
        if (type == 0) {
            res = db.rawQuery(selectLabClashingClasses, null);
        } else if (type == 1) {
            res = db.rawQuery(selectTheoryClashingClasses, null);
        }

        return res;

    }

    // for checking whether the SLOT already exists or not IN TABLE_TIMETABLE.

    public Cursor checkSlot(String slot) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_COURSE_NAME + " FROM " + TABLE_TIMETABLE + " , "
                + TABLE_COURSE + " WHERE " + COLUMN_TT_SLOT + " = '" + slot + "' AND "
                + COLUMN_COURSE_CODE + " = " + COLUMN_TT_COURSE_CODE;
        Cursor res = db.rawQuery(query, null);

        return res;
    }
}



   // ______________________________________________________________________________________________________________________________________
    /* select slot_name as slot,day,time,tt_course_code as course,faculty_name as faculty,room,faculty_cabin as cabin from faculty1, (select
slot_name,day,time,tt_faculty,tt_course_code,room from (Select * from slots),timetable1 where tt_slot = slot_name) where tt_faculty = faculty_name
and tt_course_code = faculty_course order by case WHEN Day = 'Sunday' THEN 1 WHEN Day = 'Monday' THEN 2 WHEN Day = 'Tuesday' THEN 3
WHEN Day = 'Wednesday' THEN 4  WHEN Day = 'Thursday' THEN 5 WHEN Day = 'Friday' THEN 6 WHEN Day = 'Saturday' THEN 7 end asc,time ;*/
