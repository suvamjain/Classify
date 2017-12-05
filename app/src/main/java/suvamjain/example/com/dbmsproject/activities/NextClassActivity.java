package suvamjain.example.com.dbmsproject.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.adapters.TimeTableRecyclerAdapter;
import suvamjain.example.com.dbmsproject.helpers.DividerItemDecoration;
import suvamjain.example.com.dbmsproject.modal.FillValues;
import suvamjain.example.com.dbmsproject.sql.DatabaseHelper;

/**
 * Created by SUVAM JAIN on 01-05-2017.
 */

public class NextClassActivity extends AppCompatActivity {

    private AppCompatActivity activity = NextClassActivity.this;
    private RecyclerView recyclerViewRemainingClass;
    private List<FillValues> listnextClass;
    private TimeTableRecyclerAdapter NextClassRecyclerAdapter;
    private DatabaseHelper db;
    private AppCompatTextView NoOfRemainingClass;
    private TextView textViewNoClass;
    private String dayOfTheWeek;
    private int hour;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_class);
        getSupportActionBar().setTitle("");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        //Toast.makeText(this,dayOfTheWeek.toString().toUpperCase() + " - " + hour,Toast.LENGTH_SHORT).show();



        NoOfRemainingClass = (AppCompatTextView) findViewById(R.id.tv_noOfClasses);
        textViewNoClass = (TextView) findViewById(R.id.NoRemainingClass);
        recyclerViewRemainingClass = (RecyclerView) findViewById(R.id.recyclerViewNomoreClass);

        listnextClass = new ArrayList<>();
        NextClassRecyclerAdapter = new TimeTableRecyclerAdapter(listnextClass,1);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewRemainingClass.setLayoutManager(mLayoutManager);
        recyclerViewRemainingClass.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRemainingClass.setHasFixedSize(true);
        recyclerViewRemainingClass.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewRemainingClass.setAdapter(NextClassRecyclerAdapter);

        db = new DatabaseHelper(activity);
        getClassesFromSqlite();
    }

    private void getClassesFromSqlite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listnextClass.clear();
                listnextClass.addAll(db.getRemainingClasses(dayOfTheWeek.toString().toUpperCase(),hour));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                NextClassRecyclerAdapter.notifyDataSetChanged();
                NoOfRemainingClass.setText("You have " + listnextClass.size() + " more classes for today");
                if(listnextClass.isEmpty())
                {   textViewNoClass.setVisibility(View.VISIBLE);
                    NoOfRemainingClass.setVisibility(View.GONE);
                }
            }
        }.execute();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, OptionsActivity.class));

    }
}
