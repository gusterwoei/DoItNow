package com.guster.doitnow;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Gusterwoei on 10/6/13.
 */
public class HideShowAnimation {
    private View view;
    private int mode;
    public static final int EXPAND = 0;
    public static final int COLLAPSE = 1;
    public HideShowAnimation(View view, int mode) {
        this.view = view;
        this.mode = mode;
    }

    public void startAnimation() {
        switch (mode) {
            case EXPAND:
                expand(view);
                break;
            case COLLAPSE:
                collapse(view);
                break;
        }
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density) * 2);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density) * 2);
        v.startAnimation(a);
    }

    /*
     @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if(interpolatedTime < 1.0f) {
            // default, expand the view
            int toHeight = 0;

            // collapse the view
            if(mode == this.COLLAPSE) {
                toHeight = (int)(1000 * (1 - interpolatedTime));
            } else {
                // expand the view
                toHeight = (int)(1000 * interpolatedTime);
            }

            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, toHeight));
            this.setDuration(1000);
            view.requestLayout();

            if(mode == this.COLLAPSE) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
     */
}
