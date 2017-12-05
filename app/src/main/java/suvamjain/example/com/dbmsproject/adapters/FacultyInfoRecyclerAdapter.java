package suvamjain.example.com.dbmsproject.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import suvamjain.example.com.dbmsproject.R;
import suvamjain.example.com.dbmsproject.modal.FacultyInfo;

/**
 * Created by SUVAM JAIN on 30-04-2017.
 */

public class FacultyInfoRecyclerAdapter extends RecyclerView.Adapter<FacultyInfoRecyclerAdapter.FacultyInfoViewHolder> {

    private List<FacultyInfo> facultyList;

    public FacultyInfoRecyclerAdapter(List<FacultyInfo> facultyList) { this.facultyList = facultyList; }

    @Override
    public FacultyInfoRecyclerAdapter.FacultyInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflating faculty recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faculty_info_recycler, parent, false);

        return new FacultyInfoRecyclerAdapter.FacultyInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FacultyInfoRecyclerAdapter.FacultyInfoViewHolder holder, int position) {

        holder.textViewCourseCode.setText(facultyList.get(position).getCourseCode());
        holder.textViewCourseName.setText(facultyList.get(position).getCourseName());
        holder.textViewFaculty.setText(facultyList.get(position).getFaculty());

    }


    @Override
    public int getItemCount() {
        Log.v(TimeTableRecyclerAdapter.class.getSimpleName(), "" + facultyList.size());
        return facultyList.size();
    }

    /**
     * ViewHolder class
     */
    public class FacultyInfoViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewCourseCode;
        public AppCompatTextView textViewCourseName;
        public AppCompatTextView textViewFaculty;

        public FacultyInfoViewHolder(View view) {
            super(view);
            textViewCourseCode = (AppCompatTextView) view.findViewById(R.id.textViewCode);
            textViewCourseName = (AppCompatTextView) view.findViewById(R.id.textViewCourse);
            textViewFaculty = (AppCompatTextView) view.findViewById(R.id.textViewFacultyName);
        }
    }
}

