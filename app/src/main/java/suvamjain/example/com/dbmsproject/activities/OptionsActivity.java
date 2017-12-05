package suvamjain.example.com.dbmsproject.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.adapters.HintAdapter;
import suvamjain.example.com.dbmsproject.helpers.SessionManager;
import suvamjain.example.com.dbmsproject.sql.DatabaseHelper;

import static suvamjain.example.com.dbmsproject.R.layout.spinner_item;

/**
 * Created by SUVAM JAIN on 17-04-2017.
 */

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add, showTimetable, delete, faculty_info, books, nextClass, Profile, LogOut,feedback,about;
    private NestedScrollView nestedScrollView;
    private DatabaseHelper db;
    private List<String> courses;
    private int CourseCode_pos;
    private String CourseCode_value;
    // Session Manager Class
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        getSupportActionBar().hide();


        add = (Button) findViewById(R.id.Add);
        showTimetable = (Button) findViewById(R.id.showTimetable);
        delete = (Button) findViewById(R.id.Delete);
        faculty_info = (Button) findViewById(R.id.Faculty_Info);
        books = (Button) findViewById(R.id.Books);
        nextClass = (Button) findViewById(R.id.NextClass);
        Profile = (Button) findViewById(R.id.Profile);
        LogOut = (Button) findViewById(R.id.LogOut);
        feedback = (Button) findViewById(R.id.Feedback);
        about = (Button) findViewById(R.id.About);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewOptions);

        // Session class instance
        session = new SessionManager(getApplicationContext());

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        initListeners();
    }

    private void initListeners() {
        add.setOnClickListener(this);
        showTimetable.setOnClickListener(this);
        delete.setOnClickListener(this);
        faculty_info.setOnClickListener(this);
        books.setOnClickListener(this);
        nextClass.setOnClickListener(this);
        Profile.setOnClickListener(this);
        LogOut.setOnClickListener(this);
        feedback.setOnClickListener(this);
        about.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Add:
                // Navigate to TimeTableActivity
                finish();
                Intent intentAddCourse = new Intent(getApplicationContext(), TimeTableActivity.class);
                startActivity(intentAddCourse);
                break;
            case R.id.showTimetable:
                // Navigate to UserList Activity
                finish();
                Intent intentUpdateCourse = new Intent(getApplicationContext(), UsersListActivity.class);
                startActivity(intentUpdateCourse);
                break;
            case R.id.Delete:
                LayoutInflater LI = LayoutInflater.from(OptionsActivity.this);
                View DeletePrompt = LI.inflate(R.layout.delete_prompt, null);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
                alertDialogBuilder1.setView(DeletePrompt);
                final Spinner course_spinner = (Spinner) DeletePrompt.findViewById(R.id.deleteCourse_spinner);
                db = new DatabaseHelper(getApplicationContext());
                courses = db.getAllCourseCodes(2);
                // Creating adapter for spinner
                final HintAdapter course = new HintAdapter(this, spinner_item, courses);
                course.setDropDownViewResource(R.layout.spinner_item);
                // attaching slot hintadapter to spinner
                course_spinner.setAdapter(course);
                course_spinner.setSelection(course.getCount());
                course_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parent, View arg1,
                                               int position, long arg3) {
                        CourseCode_pos = position;
                    }

                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });
                if (courses.size() == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Dialog_MinWidth);
                    builder.setCancelable(true);
                    builder.setTitle("Error");
                    builder.setMessage("You have not registered for any course.");
                    builder.setIcon(R.drawable.error);
                    builder.show();
                    return;
                    //course_spinner.setVisibility(View.GONE);
                }
                alertDialogBuilder1
                        .setCancelable(false)
                        .setPositiveButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (CourseCode_pos == course.getCount())
                                            Toast.makeText(OptionsActivity.this, "Please Select a Course", Toast.LENGTH_SHORT).show();
                                        else {
                                            CourseCode_value = course.getItem(CourseCode_pos).toString().toUpperCase();
                                            Toast.makeText(OptionsActivity.this, "Selected Course is : " + CourseCode_value, Toast.LENGTH_SHORT).show();
                                            db.deleteCourse(CourseCode_value.toString());
                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog1 = alertDialogBuilder1.create();
                // show it
                alertDialog1.show();
                break;
            case R.id.Books:
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(OptionsActivity.this);
                View promptsView = li.inflate(R.layout.prompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                final RadioGroup r = (RadioGroup) promptsView.findViewById(R.id.BookOrCourse);
                r.check(R.id.OpenSyllabus);


                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String code;
                                        if (userInput.getText().toString().trim().equalsIgnoreCase(""))
                                            Toast.makeText(getApplicationContext(), "Please enter Course Code", Toast.LENGTH_SHORT).show();
                                        else {
                                            if (r.getCheckedRadioButtonId() == R.id.OpenSyllabus)
                                                code = "syllabus/" + userInput.getText().toString().toUpperCase() + ".pdf";
                                            else
                                                code = "books/" + userInput.getText().toString().toUpperCase() + ".pdf";

                                            // get user input and send it as intent to pdf activity.
                                            finish();
                                            Intent intentBook = new Intent(getApplicationContext(), PdfActivity.class);
                                            intentBook.putExtra("Code", code);
                                            startActivity(intentBook);
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                break;
            case R.id.NextClass:
                finish();
                Intent intentNextClass = new Intent(getApplicationContext(), NextClassActivity.class);
                startActivity(intentNextClass);
                break;

            case R.id.Faculty_Info:
                finish();
                Intent intentfacultyInfo = new Intent(getApplicationContext(), FacultyInfoActivity.class);
                startActivity(intentfacultyInfo);
                break;

            case R.id.Profile:
                finish();
                Intent intentProfile = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intentProfile);
                break;
            case R.id.LogOut:
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(OptionsActivity.this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);

                // Setting Dialog Title
                alertDialog2.setTitle("Log Out...");

                // Setting Dialog Message
                alertDialog2.setMessage("Are you sure you want to Log Out?");

                // Setting Icon to Dialog
                alertDialog2.setIcon(R.drawable.logout);

                // Setting Positive "Yes" Button
                alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button. Write Logic Here

                        // Clear the session data
                        // This will clear all session data and
                        // redirect user to LoginActivity
                        session.logoutUser();
                        finish();
                        Intent options = new Intent(OptionsActivity.this, LoginActivity.class);
                        startActivity(options);
                        Toast.makeText(getApplicationContext(), "Logged Out Succesfully", Toast.LENGTH_SHORT).show();

                    }
                });

                // Setting Negative "NO" Button
                alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button. Write Logic Here
                    }
                });

                // Showing Alert Message
                alertDialog2.show();
                break;
            case R.id.Feedback:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Form");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "jainsuvam1@gmail.com" });
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.About:
                AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(OptionsActivity.this, android.R.style.Theme_Holo);
                alertDialog3.setTitle("About App");
                alertDialog3.setMessage("THIS APP IS A STUDENT FRIENDLY APP MADE FOR THE STUDENTS OF VIT UNIVERSITY. IT HELPS THE STUDENTS" +
                        " TO CHOOSE HIS/HER SLOTS IN VARIOUS COURSES TAUGHT BY DIFFERENT FACULTIES. " +
                        "IT GIVES STUDENT FREEDOM OVER THE TIMING OF HIS/HER COURSE AND WHEN HE/SHE WOULD PREFER HIS/HER THEORY OR LAB SLOTS. " +
                        "IT HAS SOME FEATURES OF FFCS WHICH ARE VERY USEFUL DURING THE COURSE REGISTRATION. " +
                        "IT ALSO CONTAINS THE INFORMATION REGARDING THE CABIN DETAILS OF VARIOUS FACULTIES. " +
                        "IT STORES PERSONAL INFO,LOGIN DETAILS AND CURRENT ACADEMIC DETAILS OF THE STUDENT" +
                        " WHICH IS ESSENTIAL FOR THE ONGOING SEMESTER.");
                alertDialog3.setIcon(R.drawable.about);
                alertDialog3.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Snackbar.make(nestedScrollView,"You have reached the home page",Snackbar.LENGTH_SHORT).show();
    }

}

