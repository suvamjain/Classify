package suvamjain.example.com.dbmsproject.activities;

/**
 * Created by SUVAM JAIN on 29-03-2017.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.adapters.TimeTableRecyclerAdapter;
import suvamjain.example.com.dbmsproject.helpers.DividerItemDecoration;
import suvamjain.example.com.dbmsproject.helpers.RecyclerTouchListener;
import suvamjain.example.com.dbmsproject.modal.FillValues;
import suvamjain.example.com.dbmsproject.sql.DatabaseHelper;

public class UsersListActivity extends AppCompatActivity {

    private AppCompatActivity activity = UsersListActivity.this;
    private TextView textViewNoCourses;
    private RecyclerView recyclerViewUsers;
    private List<FillValues> listClass;
    private TimeTableRecyclerAdapter timeTableRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }



    /**
     * This method is to initialize views
     */
    private void initViews() {

        textViewNoCourses = (TextView) findViewById(R.id.NoCourses);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {

        listClass = new ArrayList<>();
        timeTableRecyclerAdapter = new TimeTableRecyclerAdapter(listClass,0);



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewUsers.setAdapter(timeTableRecyclerAdapter);

        recyclerViewUsers.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerViewUsers,new UsersListActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                }
        }));

        databaseHelper = new DatabaseHelper(activity);

        getDataFromSQLite();

    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                listClass.clear();
                listClass.addAll(databaseHelper.getAllClasses());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                timeTableRecyclerAdapter.notifyDataSetChanged();
                if(listClass.isEmpty())
                {   textViewNoCourses.setVisibility(View.VISIBLE); }
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