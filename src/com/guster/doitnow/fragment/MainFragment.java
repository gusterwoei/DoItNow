package com.guster.doitnow.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.guster.doitnow.BadHabit;
import com.guster.doitnow.MyApplication;
import com.guster.doitnow.R;
import com.guster.doitnow.list.BadHabitListAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gusterwoei on 9/28/13.
 */
public class MainFragment extends Fragment {
    private View mContext;
    private MyApplication app;
    private List<String> datasource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = inflater.inflate(R.layout.fragment_main, container, true);
        app = (MyApplication)getActivity().getApplication();
        datasource = new ArrayList<String>();

        // configure maincontent list
        datasource = getDummyData();
        ListView list = (ListView)mContext.findViewById(R.id.list_maincontent);
        BadHabitListAdapter adapter = new BadHabitListAdapter(getActivity(), datasource);
        //SwingRightInAnimationAdapter animAdpater = new SwingRightInAnimationAdapter(adapter);
        //list.setAdapter(animAdpater);
        //animAdpater.setAbsListView(list);

        list.setAdapter(adapter);
        return mContext;
    }

    private List<String> getDummyData() {
        // children
        ArrayList<String> list = new ArrayList<String>();
        list.add("No procrastination");
        list.add("Need to sleep earlier");
        list.add("Reading regularly");

        return list;
    }

    private void setDummyData() {
        // children
        ArrayList<String> success = new ArrayList<String>();
        success.add("achieve more things!");
        success.add("increase personal wealth and health");
        success.add("life becomes more meaningful than ever");
        ArrayList<String> fail = new ArrayList<String>();
        fail.add("gonna possess the feeling of anger and disappointment");
        fail.add("wasting my own time away");
        fail.add("girls just don't like lazy guys do they?");

        // headers
        ArrayList<BadHabit> h = new ArrayList<BadHabit>();
        h.add(new BadHabit("No procrastination", success, fail));
        h.add(new BadHabit("Need to sleep earlier", success, fail));
        h.add(new BadHabit("Reading regularly", success, fail));

        HashMap<String, BadHabit> c = new HashMap<String, BadHabit>();
        for(int i=0; i<h.size(); i++) {
            c.put(h.get(i).getTitle(), h.get(i));
        }

        //headers = h;
        //children = c;
    }
}
