package nso.hh2568.utils;

import android.content.Context;
import android.content.SharedPreferences;
public class MySharedPreferences {

    public static String FIRST_INSTALL = "first";
    public static String PREFS_NAME = "MyApp";
    public static String CHECK_PERMISSION = "check_permission";

    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private static MySharedPreferences ourInstance;

    private MySharedPreferences(Context context) {
        mContext = context;
    }

    public static MySharedPreferences getInstance(Context context) {
        ourInstance = new MySharedPreferences(context);
        return ourInstance;
    }

    private void setupSharedPreferences() {
        sp = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean sharedPreferences(String key, boolean value) {
        try {
            setupSharedPreferences();
            editor.putBoolean(key, value);
            editor.commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean sharedPreferences(String key, int value) {
        try {
            setupSharedPreferences();
            editor.putInt(key, value);
            editor.commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean sharedPreferences(String key, long value) {
        try {
            setupSharedPreferences();
            editor.putLong(key, value);
            editor.commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean sharedPreferences(String key, String value) {
        try {
            setupSharedPreferences();
            editor.putString(key, value);
            editor.commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String getPrefString(String key, String defaultValue) {
        sp = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public boolean getPrefBoolean(String key, boolean defaultValue) {
        sp = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }
}
