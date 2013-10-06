package com.guster.doitnow;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gusterwoei on 9/29/13.
 */
public class MyApplication extends Application {
    public static  final String FONT_AWESOME_FILE = "fontawesome-webfont.ttf";

    public boolean setFontAwesome(View view) {
        String className= view.getClass().getSimpleName();
        if(className.equalsIgnoreCase("button")) {
            ((Button)view).setTypeface(Typeface.createFromAsset(getAssets(), FONT_AWESOME_FILE));
            return true;
        } else if(className.equalsIgnoreCase("textview")) {
            ((TextView)view).setTypeface(Typeface.createFromAsset(getAssets(), FONT_AWESOME_FILE));
            return true;
        }
        return false;
    }
}
