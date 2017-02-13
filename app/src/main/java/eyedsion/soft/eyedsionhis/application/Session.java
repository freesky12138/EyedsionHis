package eyedsion.soft.eyedsionhis.application;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Huppert on 2016/3/1.
 */
public class Session {

    private static final SharedPreferences preferences = Application.getApplication().getSharedPreferences("EyedsionHis",
            Activity.MODE_PRIVATE);


    public final static int PERSON_MODE=0;
    //public final static int GOURP_MODE=1;
    public static int PERSON_CHOOSE=0;



    public static void SetString(String key, Object value) {
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value.toString());
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SetBoolean(String key, Boolean value) {
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SetInt(String key, int value) {
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SetSetString(String key, Set<String> value) {
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putStringSet(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashSet<String> GetSetString(String key) {
        return (HashSet<String>) preferences.getStringSet(key, new HashSet<String>());
    }

    public static Boolean GetBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public static Boolean GetBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public static String GetString(String key) {
        return preferences.getString(key, "");
    }

    public static String GetString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static int GetInt(String key) {
        return preferences.getInt(key, 0);
    }

    public static int GetInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static void ClearSession() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}