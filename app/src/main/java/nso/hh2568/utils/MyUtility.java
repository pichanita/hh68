package nso.hh2568.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.widget.Toast;

import java.net.InetAddress;

//import nso.hh2568.BuildConfig;
import nso.hh2568.Callback.OnDialogCallbackListener;

/**
 * Created by nso on 15/8/2017.
 */

public class MyUtility {

    private static Context mContext;
    private static Activity mActivity;
    private static MyUtility ourInstance;
    private LocationManager mlocManager;
    private ComponentName comp;

    private MyUtility(Context context) {
        mContext = context;
    }

    public static MyUtility getInstance(Context context) {
        ourInstance = new MyUtility(context);
        return ourInstance;
    }
/*
    public String getAndroidVersion() {
        return "v." + BuildConfig.VERSION_NAME;
    }

 */

    public String getAppLable() {
        PackageManager packageManager = mContext.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(mContext.getApplicationInfo().packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "Unknown");
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }

    public void toastText(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public void showDialog(String title, String message, CharSequence okMessage) {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle(title)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(okMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                //.setIcon(R.drawable.icon_warning)
        ;
        alert.show();
    }

    public void showDialog(String title, String message, CharSequence okMessage, final OnDialogCallbackListener listener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle(title)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(okMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onOK();
                        dialog.cancel();
                    }
                })
               //.setIcon(R.drawable.icon_warning)
        ;
        alert.show();
    }

    public void showDialog(String title, String message, CharSequence okMessage, CharSequence cancelMessage, final OnDialogCallbackListener listener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle(title)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(okMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onOK();
                    }
                })
                .setNegativeButton(cancelMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
               // .setIcon(R.drawable.icon_warning
        ;
        alert.show();
    }
}
