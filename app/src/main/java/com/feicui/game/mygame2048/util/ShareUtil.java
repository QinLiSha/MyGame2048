package com.feicui.game.mygame2048.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ShareUtil {
    private static final String SHARED_PATH = "app_share";

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
    }

    public static void saveMaxScore(Context context,int maxScore) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("maxScore",maxScore);
        editor.commit();
    }

    public static int getMaxScore(Context context) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        return sharedPreferences.getInt("maxScore",0);
    }
}
