package suvamjain.example.com.dbmsproject.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.helpers.InputValidation;
import suvamjain.example.com.dbmsproject.modal.User;
import suvamjain.example.com.dbmsproject.sql.DatabaseHelper;

/**
 * Created by SUVAM JAIN on 30-03-2017.
 */

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = ForgetActivity.this;

    private NestedScrollView nestedScrollViewforget;

    private TextInputLayout textInputLayoutReg;
    private TextInputLayout textInputLayoutSecurity;
    private TextInputEditText textInputEditTextReg;
    private TextInputEditText textInputEditTextSecurity;
    private AppCompatButton appCompatButtonGetPassword;
    private AppCompatTextView appCompatTextViewLoginForget;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollViewforget = (NestedScrollView) findViewById(R.id.nestedScrollViewForget);

        textInputLayoutReg = (TextInputLayout) findViewById(R.id.textInputLayoutRegForget);
        textInputLayoutSecurity = (TextInputLayout) findViewById(R.id.textInputLayoutSecurity);

        textInputEditTextReg = (TextInputEditText) findViewById(R.id.textInputEditTextRegForget);
        textInputEditTextSecurity = (TextInputEditText) findViewById(R.id.textInputEditTextSecurity);

        appCompatButtonGetPassword = (AppCompatButton) findViewById(R.id.appCompatButtonGetPassword);

        appCompatTextViewLoginForget = (AppCompatTextView) findViewById(R.id.appCompatTextViewLinkLoginForget);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonGetPassword.setOnClickListener(this);
        appCompatTextViewLoginForget.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonGetPassword:
                GetpasswordFromSQLite();
                break;

            case R.id.appCompatTextViewLinkLoginForget:
                finish();
                break;
        }
    }



    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void GetpasswordFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextReg, textInputLayoutReg, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextReg, textInputLayoutReg, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSecurity, textInputLayoutSecurity, getString(R.string.error_message_security))) {
            return;
        }

        if (databaseHelper.checkSecurity(textInputEditTextReg.getText().toString().trim().toUpperCase(),
                textInputEditTextSecurity.getText().toString().trim())) {

            Cursor res = databaseHelper.search(textInputEditTextReg.getText().toString().trim().toUpperCase());
            StringBuffer buffer = new StringBuffer();
            if (res.moveToFirst()) {
                do{
                    buffer.append("YOUR PASSWORD IS :\t" + res.getString(3) + "\n\n");
                }while (res.moveToNext());
                showMessage(buffer.toString());
            }
            else
                buffer.append("NO DATA FOUND.\nPLEASE ENTER CORRECT SECURITY PIN\n");

            emptyInputEditText();

        } else {
            // Snack Bar to show error message that record already exists
            if (!databaseHelper.checkUser(textInputEditTextReg.getText().toString().toUpperCase().trim())) {
                Snackbar.make(nestedScrollViewforget, getString(R.string.error_email_notexist), Snackbar.LENGTH_LONG).show();
            } else
            Snackbar.make(nestedScrollViewforget,"Incorrect Registration no. or Security Hint" , Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextReg.setText(null);
        textInputEditTextSecurity.setText(null);
    }

    // fuction for popping up the alert dialogue box to show error or the PASSWORD.
    public void showMessage(String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(Message);
        builder.show();
    }
}

