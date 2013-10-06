package com.guster.doitnow.activity;
import com.guster.doitnow.MyApplication;
import com.guster.doitnow.R;
import com.guster.doitnow.list.SlidingMenuListAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private MyApplication app;
    private SlidingMenu menu;

	@SuppressLint("NewApi")
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        app = (MyApplication) getApplication();

        // configure SlidingMenu
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.sliding_menu);

        // configure sliding menu list
        ListView menulist = (ListView)findViewById(R.id.list_menulist);
        List datasource = getDummyData();
        menulist.setAdapter(new SlidingMenuListAdapter(this, datasource));
        menulist.refreshDrawableState();

        // configure action bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
	}

    /**
     * PROBLEM: becareful when you're using getItem() instead of findItem(), might result in IndexOutOfBound Exception
     * WARNING: Always remember to break after each instance of switch()
     * @param menu
     * @return
     */
	@SuppressLint("NewApi")
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(menu.findItem(R.id.menu_newpost));
        items.add(menu.findItem(R.id.menu_refresh));
        for(int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            Button b = null;
            if(item.getItemId() == R.id.menu_newpost) {
                b = (Button)findViewById(R.id.btn_fa_newpost);
                b.setText(R.string.icon_edit);
            } else if(item.getItemId() == R.id.menu_refresh) {
                b = (Button)findViewById(R.id.btn_fa_refresh);
                b.setText(R.string.icon_refresh);
            }

            if(b != null) {
                app.setFontAwesome(b);
                item.setActionView(b);
            }
        }
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                menu.toggle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String> getDummyData() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Profile");
        list.add("Setting");
        list.add("Achievement");
        list.add("Bad Habits");
        list.add("About us");
        return list;
    }
}
