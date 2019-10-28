package immotor.com.arcface.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.arcsoft.face.FaceEngine;

public class ConfigUtil {
    private static final String APP_ACTIVE = "Active";


    public static void setActive(Context context, boolean active) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ACTIVE, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putBoolean(APP_ACTIVE, active)
                .apply();
    }
    public static boolean isActived(Context context){
        if (context == null){
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ACTIVE,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(APP_ACTIVE,false);
    }

}
