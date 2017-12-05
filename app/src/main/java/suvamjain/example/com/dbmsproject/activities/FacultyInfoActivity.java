package suvamjain.example.com.dbmsproject.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.adapters.FacultyInfoRecyclerAdapter;
import suvamjain.example.com.dbmsproject.helpers.DividerItemDecoration;
import suvamjain.example.com.dbmsproject.helpers.RecyclerTouchListener;
import suvamjain.example.com.dbmsproject.modal.FacultyInfo;
import suvamjain.example.com.dbmsproject.sql.DatabaseHelper;

/**
 * Created by SUVAM JAIN on 30-04-2017.
 */

public class FacultyInfoActivity extends AppCompatActivity {

    private AppCompatActivity activity = FacultyInfoActivity.this;
    private RecyclerView recyclerViewFaculty;
    private List<FacultyInfo> facultyList;
    private FacultyInfoRecyclerAdapter facultyInfoRecyclerAdapter;
    private DatabaseHelper db;
    private AppCompatTextView textViewNoOfFaculty;
    private TextView textViewNoFaculty;

    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_list);
        getSupportActionBar().setTitle("");
        recyclerViewFaculty = (RecyclerView) findViewById(R.id.recyclerViewFaculty);
        textViewNoOfFaculty = (AppCompatTextView) findViewById(R.id.tv_noOfFaculty);
        textViewNoFaculty = (TextView) findViewById(R.id.NoFaculty);
        facultyList = new ArrayList<>();
        facultyInfoRecyclerAdapter = new FacultyInfoRecyclerAdapter(facultyList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewFaculty.setLayoutManager(mLayoutManager);
        recyclerViewFaculty.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFaculty.setHasFixedSize(true);
        recyclerViewFaculty.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewFaculty.setAdapter(facultyInfoRecyclerAdapter);

        recyclerViewFaculty.addOnItemTouchListener(new RecyclerTouchListener(FacultyInfoActivity.this,recyclerViewFaculty,new FacultyInfoActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                FacultyInfo facultyinfo = facultyList.get(position);
                alertDialog = new AlertDialog.Builder(FacultyInfoActivity.this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
                alertDialog.setCancelable(true);
                alertDialog.setIcon(R.drawable.faculty);
                alertDialog.setTitle("" + facultyinfo.getFaculty());
                alertDialog.setMessage("Cabin : " + facultyinfo.getCabin() + "\n");
                alertDialog.show();
            }

            @Override
            public void onLongClick(View view, int position) {
                FacultyInfo facultyinfo = facultyList.get(position);
            }

        }));

        db = new DatabaseHelper(activity);
        getFacultyFromSQLite();

    }

    private void getFacultyFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                facultyList.clear();
                facultyList.addAll(db.getAllFacultyInfo());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                facultyInfoRecyclerAdapter.notifyDataSetChanged();
                textViewNoOfFaculty.setText(facultyList.size() + " Faculty Found");
                if(facultyList.isEmpty())
                {   textViewNoFaculty.setVisibility(View.VISIBLE);
                    textViewNoOfFaculty.setVisibility(View.GONE);
                }
            }
        }.execute();
    }


    public interface ClickListener {
        void onLongClick(View child, int childPosition);

        void onClick(View child, int childPosition);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, OptionsActivity.class));

    }


}
