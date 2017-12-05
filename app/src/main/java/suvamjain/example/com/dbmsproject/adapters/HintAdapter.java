package suvamjain.example.com.dbmsproject.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by SUVAM JAIN on 15-04-2017.
 */

public class HintAdapter extends ArrayAdapter<String> {



    public HintAdapter(Context theContext, int theLayoutResId, List<String> objects) {
        super(theContext,theLayoutResId,objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
