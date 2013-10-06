package com.guster.doitnow.list;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.guster.doitnow.BadHabit;
import com.guster.doitnow.MyApplication;
import com.guster.doitnow.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gusterwoei on 9/28/13.
 */
public class BadHabitListAdapter2 extends BaseExpandableListAdapter {
    private static final int HEADER_LAYOUT_ID = R.layout.listitem_badhabit;
    private static final int ITEM_LAYOUT_ID = R.layout.listitem_badhabitsub;
    private Context mContext;
    private List<BadHabit> headers;
    private HashMap<String, BadHabit> children;

    public BadHabitListAdapter2(Context context, List headers, HashMap<String, BadHabit> children) {
        mContext = context;
        this.headers = headers;
        this.children = children;
        Log.d("DOITNOW", "children size = " + children.size());
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.HEADER_LAYOUT_ID, viewGroup, false);
        }

        TextView title = (TextView)view.findViewById(R.id.txt_title);
        title.setText(headers.get(position));

        TextView severity = (TextView)view.findViewById(R.id.txt_severity);
        severity.setText("" + position);

        // setting up the sublist items
        ListView list = (ListView)view.findViewById(R.id.list_success);
        setSublistDatasource(list, getDummyData("SUCCESS"));

        list = (ListView)view.findViewById(R.id.list_fail);
        setSublistDatasource(list, getDummyData("FAIL"));

        return view;
    }
    */

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return headers.size();
    }

    @Override
    public int getChildrenCount(int i) {
        Log.d("DOITNOW", "couting = " + children.size());
        return children.size();
    }

    @Override
    public Object getGroup(int i) {
        return headers.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return children.get(headers.get(i).getTitle());
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int position, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.HEADER_LAYOUT_ID, viewGroup, false);
        }
        BadHabit bh = (BadHabit)getGroup(position);

        TextView title = (TextView)view.findViewById(R.id.txt_title);
        title.setText(bh.getTitle());

        TextView severity = (TextView)view.findViewById(R.id.txt_severity);
        severity.setText("" + position);

        // setting up the sublist items
        /*
        ListView list = (ListView)view.findViewById(R.id.list_success);
        setSublistDatasource(list, getDummyData("SUCCESS"));
        list = (ListView)view.findViewById(R.id.list_fail);
        setSublistDatasource(list, getDummyData("FAIL"));
        */
        return view;
    }

    @Override
    public View getChildView(int i, int i2, boolean isLastChild, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.ITEM_LAYOUT_ID, viewGroup, false);
        }

        Log.d("DOITNOW", "got ah");
        BadHabit bh = (BadHabit)getChild(i, i2);

        MyApplication app = (MyApplication)mContext.getApplicationContext();
        app.setFontAwesome(view.findViewById(R.id.txt_indicator));

        TextView title = (TextView)view.findViewById(R.id.txt_title);
        title.setText(bh.getSuccess().get(0));
        title.setText("hello");

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        Log.d("DOITNOW", "got ahh");
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    private void setSublistDatasource(ListView list, List datasource) {
        list.setAdapter(new BadHabitSublistAdapter(mContext, datasource));
    }

    private List getDummyData(String id) {
        List list = new ArrayList<String>();
        if(id.equalsIgnoreCase("SUCCESS")) {
            list.add("achieve more things!");
            list.add("increase personal wealth and health");
            list.add("life becomes more meaningful than ever");
        } else if(id.equalsIgnoreCase("FAIL")) {
            list.add("gonna possess the feeling of anger and disappointment");
            list.add("wasting my own time away");
            list.add("girls just don't like lazy guys do they?");
        }

        Log.d("DOITNOW", id + ": " + list.size());
        return list;
    }
}
