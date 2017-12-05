package suvamjain.example.com.dbmsproject.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.activities.VerticalTextView;
import suvamjain.example.com.dbmsproject.modal.FillValues;

/**
 * Created by SUVAM JAIN on 23-04-2017.
 */

public class TimeTableRecyclerAdapter extends RecyclerView.Adapter<TimeTableRecyclerAdapter.TimeTableViewHolder> {

    private List<FillValues> listClass;
    private int x;      // x = 0 is used for dailytimetable activity and weekday is also shown.1 is used for next class activity where day is made invisible.


    public TimeTableRecyclerAdapter(List<FillValues> listClass,int x) { this.listClass = listClass; this.x = x;}

    @Override
    public TimeTableRecyclerAdapter.TimeTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_timetable_recycler, parent, false);

        return new TimeTableRecyclerAdapter.TimeTableViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(TimeTableRecyclerAdapter.TimeTableViewHolder holder, int position) {
        holder.textViewSlot.setText(listClass.get(position).getSlot());
        holder.textViewDay.setText(listClass.get(position).getDay());
        holder.textViewStartTime.setText(listClass.get(position).getStartTime() + "-" + listClass.get(position).getEndTime());
       // holder.textViewEndTime.setText(listClass.get(position).getEndTime());
        holder.textViewCourseCode.setText(listClass.get(position).getCourseCode());
        holder.textViewCourseName.setText(listClass.get(position).getCourseName());
        holder.textViewFaculty.setText(listClass.get(position).getFaculty());
        holder.textViewRoom.setText(listClass.get(position).getRoom());

    }

    @Override
    public int getItemCount() {
        Log.v(TimeTableRecyclerAdapter.class.getSimpleName(),""+listClass.size());
        return listClass.size();
    }


    /**
     * ViewHolder class
     */
    public class TimeTableViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewDay;
        public AppCompatTextView textViewDayCaption;
        //public AppCompatTextView textViewStartTime;
        public VerticalTextView textViewStartTime;
        public AppCompatTextView textViewSlot;
        public AppCompatTextView textViewCourseCode;
        public AppCompatTextView textViewCourseName;
        public AppCompatTextView textViewFaculty;
        public AppCompatTextView textViewRoom;


        public TimeTableViewHolder (View view) {
            super(view);
            textViewDay = (AppCompatTextView) view.findViewById(R.id.textViewDay);
            textViewStartTime = (VerticalTextView) view.findViewById(R.id.textViewStartTime);
            textViewSlot = (AppCompatTextView) view.findViewById(R.id.textViewSlot);
            textViewCourseCode = (AppCompatTextView) view.findViewById(R.id.textViewCourseCode);
            textViewCourseName = (AppCompatTextView) view.findViewById(R.id.textViewCourseName);
            textViewFaculty = (AppCompatTextView) view.findViewById(R.id.textViewFaculty);
            textViewRoom = (AppCompatTextView) view.findViewById(R.id.textViewRoom);
            textViewDayCaption = (AppCompatTextView) view.findViewById(R.id.recycler_day);

            if(x==1)    //i.e it is called by next class so hide the day row in recycler view.
            {textViewDayCaption.setVisibility(View.GONE);
                textViewDay.setVisibility(View.GONE);
            }
        }
    }

}
