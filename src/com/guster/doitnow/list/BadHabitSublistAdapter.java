package com.guster.doitnow.list;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.guster.doitnow.MyApplication;
import com.guster.doitnow.R;

import java.util.List;

/**
 * Created by Gusterwoei on 9/28/13.
 */
public class BadHabitSublistAdapter extends BaseAdapter {
    private static final int ITEM_LAYOUT_ID = R.layout.listitem_badhabitsub;
    private Context mContext;
    private List<String> mDatasource;

    public BadHabitSublistAdapter(Context context, List datasource) {
        mContext = context;
        mDatasource = datasource;
    }

    @Override
    public int getCount() {
        return mDatasource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatasource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.ITEM_LAYOUT_ID, viewGroup, false);
        }

        MyApplication app = (MyApplication)mContext.getApplicationContext();
        app.setFontAwesome(view.findViewById(R.id.txt_indicator));

        TextView title = (TextView)view.findViewById(R.id.txt_title);
        title.setText(mDatasource.get(position));

        return view;
    }
}
