package suvamjain.example.com.dbmsproject.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.adapters.HintAdapter;
import suvamjain.example.com.dbmsproject.sql.DatabaseHelper;

import static suvamjain.example.com.dbmsproject.R.id.CourseCode_spinner;
import static suvamjain.example.com.dbmsproject.R.layout.spinner_item;

/**
 * Created by SUVAM JAIN on 16-04-2017.
 */

public class TimeTableActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Spinner slot_spinner,course_code,faculty_spinner,RoomNo_spinner;
    RadioGroup courseType;
    private AppCompatButton appCompatButtonSaveCourse;
    private NestedScrollView nestedScrollView;
    private int CourseCode_pos;
    private String CourseCode_value;
    private int Slot_pos;
    private String Slot_value;
    private int Faculty_pos;
    private String Faculty_value;
    private int Room_pos;
    private String Room_value;
    private HintAdapter slot,code,Room_no,faculty;
    AlertDialog.Builder alertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__time_table);
        getSupportActionBar().hide();

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewTimeTable);
        appCompatButtonSaveCourse = (AppCompatButton) findViewById(R.id.appCompatButtonSave_Course);
        slot_spinner = (Spinner) findViewById(R.id.Slot_spinner);
        course_code = (Spinner) findViewById(CourseCode_spinner);
        RoomNo_spinner = (Spinner) findViewById(R.id.RoomNo_spinner);
        faculty_spinner = (Spinner) findViewById(R.id.Faculty_spinner);


        appCompatButtonSaveCourse.setOnClickListener(this);
        //android.R.style.Theme_DeviceDefault_Dialog_Alert




        slot_spinner.setOnItemSelectedListener(this);
        course_code.setOnItemSelectedListener(this);
        RoomNo_spinner.setOnItemSelectedListener(this);
        faculty_spinner.setOnItemSelectedListener(this);



        final List<String> Room = new ArrayList<String>();
        for(int j = 1;j<9;j++)
            for (int i = j*100 + 1; i < j*100 +15; i++)
                Room.add("AB1-" + i);
        // add hint as last item
        Room.add("Select Room");

        Room_no = new HintAdapter(this, spinner_item, Room);
        Room_no.setDropDownViewResource(R.layout.spinner_item);


        RoomNo_spinner.setAdapter(Room_no);
        // show hint
        RoomNo_spinner.setSelection(Room_no.getCount());

        // for  loading all the course codes from database;
        loadFaculty();

        courseType = (RadioGroup) findViewById(R.id.CourseType);
        // TO get which type of course is selected by default we need to set 1 courseType as checked already here(theory).
       // courseType.check(R.id.theory);

        courseType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton Type = (RadioButton) findViewById(courseType.getCheckedRadioButtonId());

                if (Type.getText().toString().toUpperCase().equals("THEORY")) {
                    loadSlots(0);
                    loadCourseCodes(0);
                }
                else if (Type.getText().toString().toUpperCase().equals("LAB")) {
                    alertDialog = new AlertDialog.Builder(TimeTableActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                    alertDialog.setCancelable(true);
                    alertDialog.setTitle("Point To Remember");
                    alertDialog.setIcon(R.drawable.alert);
                    alertDialog.setMessage("If your course has 3 Lab slots\nFor e.g. L1-L2/L3-L4/L5-L6\nRegister the same course 3 times with each pair of lab");
                    alertDialog.show();

                    loadSlots(1);
                    loadCourseCodes(1);
                }

            }
    });

    }

    private void loadSlots(int x) {
        // database handler
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        List<String> slots;
        slots = db.getAllSlots(x);

        // Creating adapter for spinner
        slot = new HintAdapter(this, spinner_item, slots);
        slot.setDropDownViewResource(R.layout.spinner_item);

        // attaching slot hintadapter to spinner
        slot_spinner.setAdapter(slot);
        slot_spinner.setSelection(slot.getCount());
    }

    private void loadCourseCodes(int x) {
        // database handler
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        List<String> codes;
        codes = db.getAllCourseCodes(x);

        // Creating adapter for spinner
        code = new HintAdapter(this, spinner_item, codes);
        code.setDropDownViewResource(R.layout.spinner_item);

        // attaching slot hintadapter to spinner
        course_code.setAdapter(code);
        course_code.setSelection(code.getCount());
    }

    private void loadFaculty() {
        // database handler
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        List<String> faculties;
        faculties = db.getAllFaculty();

        // Creating adapter for spinner
        faculty = new HintAdapter(this, spinner_item, faculties);
        faculty.setDropDownViewResource(R.layout.spinner_item);

        // attaching slot hintadapter to spinner
        faculty_spinner.setAdapter(faculty);
        faculty_spinner.setSelection(faculty.getCount());
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonSave_Course:
                if(courseType.getCheckedRadioButtonId() == -1)
                {   Snackbar.make(nestedScrollView,"Please Select a Course Type",Snackbar.LENGTH_LONG).show();
                    return;
                    // Toast.makeText(getApplicationContext(),"first radiogroup is null",Toast.LENGTH_LONG).show();
                }
                if(Slot_pos == slot.getCount())
                {
                    Snackbar.make(nestedScrollView,"Please Select a Slot",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(CourseCode_pos == code.getCount())
                {
                    Snackbar.make(nestedScrollView,"Please Select a Course Code",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(Room_pos == Room_no.getCount())
                {
                    Snackbar.make(nestedScrollView,"Please Select a Room",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(Faculty_pos == faculty.getCount())
                {
                    Snackbar.make(nestedScrollView,"Please Select a Faculty",Snackbar.LENGTH_LONG).show();
                    return;
                }
                Slot_value = slot.getItem(Slot_pos).toString().toUpperCase();
                CourseCode_value = code.getItem(CourseCode_pos).toString().toUpperCase();
                Faculty_value = faculty.getItem(Faculty_pos).toString().toUpperCase();
                Room_value = Room_no.getItem(Room_pos).toString().toUpperCase();

                final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                // added extra for slots like 'A1+TA1'.
                if(Slot_value.contains("+"))
                {
                    String Slotparts[] = Slot_value.split("\\+");
//                    Toast.makeText(TimeTableActivity.this, Slotparts[0] + " and " + Slotparts[1] , Toast.LENGTH_SHORT).show();
                    StringBuffer buffer = new StringBuffer();
                    Cursor r1 = db.checkSlot(Slotparts[0]);
                    Cursor r2 = db.checkSlot(Slotparts[1]);

                    if(r1.getCount() > 0 || r2.getCount() > 0)
                    {
                        if (r1.getCount() > 0) {
                            buffer.append(Slotparts[0] + " is already registered for " );
                            while (r1.moveToNext()) {
                                buffer.append(r1.getString(0) + "\n");
                            }
                        }
                        if (r2.getCount() > 0) {
                            buffer.append(Slotparts[1] + " is already registered for " );
                            while (r2.moveToNext()) {
                                buffer.append(r2.getString(0) + "\n");
                            }
                        }

                        alertDialog = new AlertDialog.Builder(TimeTableActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                        alertDialog.setCancelable(true);
                        alertDialog.setTitle("Error...");
                        alertDialog.setIcon(R.drawable.error);
                        alertDialog.setMessage(buffer.toString());
                        alertDialog.show();
                        return;
                    }

                    if (courseType.getCheckedRadioButtonId() == R.id.theory) {

                        StringBuffer buffer1 = new StringBuffer();

                        r1 = db.getAllClashingClass(Slotparts[0], 0);
                        r2 =  db.getAllClashingClass(Slotparts[1], 0);

                        if(r1.getCount() > 0 || r2.getCount() > 0)
                        {
                            if (r1.getCount() > 0) {
                                while (r1.moveToNext()) {
                                    buffer1.append(r1.getString(1) + " slot on " + r1.getString(0) + "\n");
                                }
                            }
                            if (r2.getCount() > 0) {
                                while (r2.moveToNext()) {
                                    buffer1.append(r2.getString(1) + " slot on " + r2.getString(0) + "\n");
                                }
                            }

                                alertDialog = new AlertDialog.Builder(TimeTableActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                                alertDialog.setCancelable(true);
                                alertDialog.setTitle("Error...");
                                alertDialog.setIcon(R.drawable.error);
                                alertDialog.setMessage( Slot_value + " slot clashes with :\n\n " + buffer1.toString());
                                alertDialog.show();
                                return;
                            }
                        }
                    }
                else {
                        Cursor res = db.checkSlot(Slot_value);

                    if (res.getCount() > 0) {
                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) {
                            buffer.append(res.getString(0) + "\n");
                        }


                        alertDialog = new AlertDialog.Builder(TimeTableActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                        alertDialog.setCancelable(true);
                        alertDialog.setTitle("Error...");
                        alertDialog.setIcon(R.drawable.error);
                        alertDialog.setMessage(Slot_value + " slot is already registered for " + buffer.toString());
                        alertDialog.show();
                        return;
                    }
                    if (courseType.getCheckedRadioButtonId() == R.id.theory) {
                        Cursor r = db.getAllClashingClass(Slot_value, 0);
                        if (r.getCount() > 0) {
                            StringBuffer buffer = new StringBuffer();

                            while (r.moveToNext()) {
                                buffer.append(r.getString(1) + " slot on " + r.getString(0) + "\n");
                            }

                            alertDialog = new AlertDialog.Builder(TimeTableActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                            alertDialog.setCancelable(true);
                            alertDialog.setTitle("Error...");
                            alertDialog.setIcon(R.drawable.error);
                            alertDialog.setMessage(Slot_value + " slot clashes with :\n\n " + buffer.toString());
                            alertDialog.show();
                            return;
                        }
                    } else if (courseType.getCheckedRadioButtonId() == R.id.lab) {
                        Cursor r = db.getAllClashingClass(Slot_value, 1);
                        if (r.getCount() > 0) {
                            StringBuffer buffer = new StringBuffer();

                            while (r.moveToNext()) {
                                buffer.append(r.getString(0) + " slot \n");
                            }

                            alertDialog = new AlertDialog.Builder(TimeTableActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                            alertDialog.setCancelable(true);
                            alertDialog.setTitle("Error...");
                            alertDialog.setIcon(R.drawable.error);
                            alertDialog.setMessage(Slot_value + " slot clashes with :\n\n " + buffer.toString());
                            alertDialog.show();
                            return;
                        }
                    }
                }

                alertDialog = new AlertDialog.Builder(TimeTableActivity.this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);

                // Setting Dialog Title
                alertDialog.setTitle("Save Course...");

                // Setting Dialog Message
                alertDialog.setMessage("Do you want to save this course?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.save);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button. Write Logic Here
                        if(Slot_value.contains("+"))
                        {   String Slots[] = Slot_value.split("\\+");
                            db.addTimeTable(Slots[0],CourseCode_value,Room_value,Faculty_value);
                            db.addTimeTable(Slots[1],CourseCode_value,Room_value,Faculty_value);
                        }
                        else
                        db.addTimeTable(Slot_value,CourseCode_value,Room_value,Faculty_value);

                        finish();
                        Toast.makeText(TimeTableActivity.this,"Your course has been saved successfully.",Toast.LENGTH_SHORT).show();
                        Intent intentactivity = new Intent(getApplicationContext(), OptionsActivity.class);
                        startActivity(intentactivity);
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button. Write Logic Here
                    }
                });

                // Showing Alert Message
                alertDialog.show();
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case CourseCode_spinner:
                CourseCode_pos = position;
                break;
            case R.id.Slot_spinner:
                Slot_pos = position;
                break;
            case R.id.Faculty_spinner:
                Faculty_pos = position;
                break;
            case R.id.RoomNo_spinner:
                Room_pos = position;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, OptionsActivity.class));

    }


}
