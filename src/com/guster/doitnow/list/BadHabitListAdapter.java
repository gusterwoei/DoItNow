package com.guster.doitnow.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.guster.doitnow.HideShowAnimation;
import com.guster.doitnow.MyApplication;
import com.guster.doitnow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gusterwoei on 9/28/13.
 */
//public class BadHabitListAdapter extends com.haarman.listviewanimations.ArrayAdapter<Object> {
public class BadHabitListAdapter extends ArrayAdapter {
    private static final int HEADER_LAYOUT_ID = R.layout.listitem_badhabit;
    private Context mContext;
    private MyApplication app;
    private List<String> datasource;

    public BadHabitListAdapter(Context context, List datasource) {
        //super(datasource);
        super(context, HEADER_LAYOUT_ID, datasource);
        this.app = (MyApplication)context.getApplicationContext();
        this.mContext = context;
        this.datasource = datasource;
    }

    @Override
    public int getCount() {
        return datasource.size();
    }

    @Override
    public Object getItem(int i) {
        return datasource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null) {
            view = inflater.inflate(this.HEADER_LAYOUT_ID, viewGroup, false);
        }

        TextView title = (TextView)view.findViewById(R.id.txt_title);
        title.setText(datasource.get(position));

        TextView severity = (TextView)view.findViewById(R.id.txt_severity);
        severity.setText("" + position);

        // setting up the sublist items
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.lyt_success);
        layout.removeAllViews();
        setSublistDatasource(layout, getDummyData("SUCCESS"));
        layout = (LinearLayout)view.findViewById(R.id.lyt_fail);
        layout.removeAllViews();
        setSublistDatasource(layout, getDummyData("FAIL"));


        // setting up view's event listener
        LinearLayout titleLayout = (LinearLayout)view.findViewById(R.id.lyt_title);
        setViewOnClickListener(titleLayout, view);

        return view;
    }

    private void setViewOnClickListener(View view, final View parent) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.lyt_title: {
                        LinearLayout layout = (LinearLayout)parent.findViewById(R.id.lyt_badhabitsublistitem);

                        int animMode = HideShowAnimation.EXPAND;
                        switch (layout.getVisibility()) {
                            case View.VISIBLE:
                                animMode = HideShowAnimation.COLLAPSE;
                                break;
                        }

                        // list expand/collapse animation
                        HideShowAnimation anim = new HideShowAnimation(layout, animMode);
                        anim.startAnimation();
                        break;
                    }
                    case R.id.lyt_badhabitsublistitem: {
                        break;
                    }
                }
            }
        });
    }

    private void setSublistDatasource(LinearLayout layout, List datasource) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0; i < datasource.size(); i++) {
            View itemView = inflater.inflate(R.layout.listitem_badhabitsub, null);
            View divider = inflater.inflate(R.layout.divider, null);

            // set item indicator and text
            TextView indicator = (TextView)itemView.findViewById(R.id.txt_indicator);
            app.setFontAwesome(indicator);
            TextView tv = (TextView)itemView.findViewById(R.id.txt_title);
            tv.setText((String)datasource.get(i));

            // set onClick listener
            setViewOnClickListener(itemView, null);

            // add new item into the layout
            layout.addView(itemView);
            layout.addView(divider);
        }
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
        return list;
    }
}
