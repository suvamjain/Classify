package suvamjain.example.com.dbmsproject.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import suvamjain.example.com.dbmsproject.activities.FacultyInfoActivity;
import suvamjain.example.com.dbmsproject.activities.UsersListActivity;

/**
 * Created by SUVAM JAIN on 26-04-2017.
 */


@SuppressWarnings("ALL")
public  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private UsersListActivity.ClickListener clickListener;
    private FacultyInfoActivity.ClickListener clickListener1;


    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final UsersListActivity.ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    public RecyclerTouchListener(FacultyInfoActivity context, final RecyclerView recyclerViewFaculty, final FacultyInfoActivity.ClickListener clickListenerFaculty) {
        this.clickListener1 = clickListenerFaculty;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerViewFaculty.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListenerFaculty != null) {
                    clickListenerFaculty.onLongClick(child, recyclerViewFaculty.getChildPosition(child));
                }
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        if (child != null && clickListener1 != null && gestureDetector.onTouchEvent(e)) {
            clickListener1.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}

