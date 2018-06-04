package com.example.marcin.mytvapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.app.BackgroundManager;
import android.util.DisplayMetrics;

class SimpleBackgroundManager {

    private static Drawable mDefaultBackground;

    private BackgroundManager mBackgroundManager;

    SimpleBackgroundManager(Activity activity) {
        int DEFAULT_BACKGROUND_RES_ID = R.drawable.lb_background;
        mDefaultBackground = activity.getDrawable(DEFAULT_BACKGROUND_RES_ID);
        mBackgroundManager = BackgroundManager.getInstance(activity);
        mBackgroundManager.attach(activity.getWindow());
        activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
    }

    void updateBackground(Drawable drawable) {
        mBackgroundManager.setDrawable(drawable);
    }

    void clearBackground() {
        mBackgroundManager.setDrawable(mDefaultBackground);
    }

}