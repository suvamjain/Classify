package suvamjain.example.com.dbmsproject.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.adapters.HintAdapter;
import suvamjain.example.com.dbmsproject.helpers.InputValidation;
import suvamjain.example.com.dbmsproject.sql.DatabaseHelper;

import static suvamjain.example.com.dbmsproject.R.layout.spinner_item;

/**
 * Created by SUVAM JAIN on 12-04-2017.
 */

@SuppressWarnings("deprecation")
public class InfoActivity extends AppCompatActivity {

    private final AppCompatActivity activity = InfoActivity.this;
    private Button DOB;
    private AppCompatTextView DOB_value;
    private String date;
    private NestedScrollView nestedScrollView;
    private static final String TAG = "InfoActivity";
    private int semester_spinner_pos;
    private String semester_value;
    private int admission_spinner_pos;
    private String admission_value;

    private AppCompatButton appCompatButtonSave;

    private InputValidation inputValidation;

    private TextInputLayout textInputLayoutName;
    private TextInputEditText textInputEditTextName;
    private AppCompatTextView textInputTextReg;
    private DatabaseHelper db;

    private Spinner semester_spinner,admission_spinner;

    private String n,g,d,y,s,r,pas,sec;
    private int id;
    private  RadioGroup radioGroup;
    private RadioButton male,female;


    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().hide();

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewInfo);
        db = new DatabaseHelper(getApplicationContext());

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName_info);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName_info);
        textInputTextReg = (AppCompatTextView) findViewById(R.id.textViewReg_info_value);
        DOB_value = (AppCompatTextView) findViewById(R.id.textInputDOB_info_value);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        inputValidation = new InputValidation(activity);

        appCompatButtonSave = (AppCompatButton) findViewById(R.id.appCompatButtonSave_info);

        final List<String> semester = new ArrayList<String>();
        for(int i = 1;i< 9;i++)
            semester.add(""+ i);
        // add hint as last item
        semester.add("Select Semester");

        final HintAdapter semester_no= new HintAdapter(this, spinner_item, semester);
        semester_no.setDropDownViewResource(R.layout.spinner_item);

        semester_spinner = (Spinner) findViewById(R.id.semester_spinner);
        semester_spinner.setAdapter(semester_no);
        // show hint
        semester_spinner.setSelection(semester_no.getCount());

       // Do this stuff to get the item selected from the spinner.
        semester_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semester_spinner_pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> year = new ArrayList<String>();
        for(int i =2010;i<2051;i++)
            year.add(""+ i);
        // add hint as last item
        year.add("Select Year");

        final HintAdapter admission = new HintAdapter(this, spinner_item, year);
        admission.setDropDownViewResource(R.layout.spinner_item);

        admission_spinner = (Spinner) findViewById(R.id.admission_spinner);
        admission_spinner.setAdapter(admission);
        // show hint
        admission_spinner.setSelection(admission.getCount());

        admission_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            admission_spinner_pos = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        // TO get which gender is selected by default we need to set 1 gender as checked already here(male).
//        radioGroup.check(R.id.male);

        Cursor c = db.getDetails();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                id = c.getInt(0);
                n = c.getString(1);
                r = c.getString(2);
                pas = c.getString(3);
                sec = c.getString(4);
                d = c.getString(5);
                g = c.getString(6);
                y = c.getString(7);
                s = c.getString(8);
            }
        }
//        Toast.makeText(this,"Database returned this value\n " + id +"-"+ n+ "-"+ r+ "-"+
//                pas + "-"+sec+ "-"+ d+ "-"+g+ "-"+y+ "-"+s,Toast.LENGTH_LONG).show();

            textInputEditTextName.setText(n);
            textInputTextReg.setText(r);
            if(s != null)
            semester_spinner.setSelection(semester_no.getPosition(s));
            if(y != null)
            admission_spinner.setSelection(admission.getPosition(y));
//            if(d != null)
            {
                DOB_value.setText(d);
                date = d;
            }

            if(male.getText().toString().toUpperCase().equals(g))
                radioGroup.check(R.id.male);
            if(female.getText().toString().toUpperCase().equals(g))
              radioGroup.check(R.id.female);

        DOB = (Button) findViewById(R.id.SelectDOB);

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        InfoActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setMessage("Select Date Of Birth");
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year);
                date = dayOfMonth + "/" + month + "/" + year;
                DOB_value.setText(date);
            }
        };


        appCompatButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
                    return;
                }
                if (date == null)
                {
                    Snackbar.make(nestedScrollView,"Please Select Date Of Birth",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(radioGroup.getCheckedRadioButtonId()== -1)
                {
                    Snackbar.make(nestedScrollView,"Please Select Your Sex",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(admission_spinner_pos == admission.getCount())
                {
                    Snackbar.make(nestedScrollView,"Please Select Year of Admission",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(semester_spinner_pos == semester_no.getCount())
                {
                    Snackbar.make(nestedScrollView,"Please Select Semester",Snackbar.LENGTH_LONG).show();
                    return;
                }
                admission_value = admission.getItem(admission_spinner_pos).toString();
                semester_value = semester_no.getItem(semester_spinner_pos).toString();

                RadioButton gender = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

                String name = textInputEditTextName.getText().toString().toUpperCase();
                String sex = gender.getText().toString().toUpperCase();
                String dob = DOB_value.getText().toString().trim();
                String sem = semester_value;
                String yoa = admission_value;

//                Toast.makeText(InfoActivity.this,"Inserting into db user " + id + name + sex + dob + sem + yoa + r + pas +sec,Toast.LENGTH_LONG).show();

                db.updateUser(id,name,r,pas,sec,sex,dob,yoa,sem);
                finish();
                Toast.makeText(InfoActivity.this,"Your details are saved successfully.",Toast.LENGTH_SHORT).show();
                Intent optionsActivity = new Intent(activity, OptionsActivity.class);
                startActivity(optionsActivity);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, OptionsActivity.class));

    }

}