package nso.hh2568;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;

import nso.hh2568.Callback.RPResultListener;
import nso.hh2568.utils.DataBase;
import nso.hh2568.utils.Global;
import nso.hh2568.utils.MySharedPreferences;
import nso.hh2568.utils.MyUtility;
import nso.hh2568.utils.RuntimePermissionUtil;
import android.os.StrictMode;
import android.provider.Settings;
import android.app.AlertDialog;
import android.app.ProgressDialog;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private boolean haspermission = false;
    private String[] perm;
    private DataBase db;
    private String email = "", password = "";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        MySharedPreferences.getInstance(this).sharedPreferences(MySharedPreferences.FIRST_INSTALL, true);

        haspermission = MySharedPreferences.getInstance(getApplicationContext()).getPrefBoolean(MySharedPreferences.CHECK_PERMISSION, false);
        if (haspermission || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            setup();
        } else {
            perm = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION};
            RuntimePermissionUtil.requestPermission(this, perm, 100);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                @Override
                public void onPermissionGranted() {
                    if (RuntimePermissionUtil.checkPermissonGranted(MainActivity.this, perm)) {
                        MySharedPreferences.getInstance(getApplicationContext()).sharedPreferences(MySharedPreferences.CHECK_PERMISSION, true);
                        if(MySharedPreferences.getInstance(MainActivity.this).getPrefBoolean(MySharedPreferences.FIRST_INSTALL,false)) {
                            MySharedPreferences.getInstance(MainActivity.this).sharedPreferences(MySharedPreferences.FIRST_INSTALL, false);
                            restartActivity();
                        }
                    }
                }

                @Override
                public void onPermissionDenied() {
                    // do nothing
                }
            });
        }
    }

    private void restartActivity() {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }


    private void setup() {


        //declare database variable
        db = new DataBase(this);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        Button email_clear_button = (Button) findViewById(R.id.email_clear_button);


        email_clear_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPasswordView.setText("");
                mEmailView.setText("");
            }
        });

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnLoginClick();
            }
        });

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            GetSN2();
        }
        else
        {
            GetSN();
        }


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void GetSN2() {
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Global.getInstance().set_sn(androidId);
    }

    private void GetSN() {
        // TODO Auto-generated method stub
        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //hh62
        String IMEIsString = telephonyManager.getDeviceId();



        //hh63
        if(IMEIsString.equals("") )
        {
            try
            {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    IMEIsString = telephonyManager.getImei();
                }
                else {
                    IMEIsString = Build.SERIAL;
                }
            }
            catch (Exception e){}

        }

        Global.getInstance().set_sn(IMEIsString);


    }
    public void onBtnLoginClick() {
        if (!mEmailView.getText().toString().equals("") && !mPasswordView.getText().toString().equals("")) {
            new LoginProcessing().execute();
        } else {
            MyUtility.getInstance(MainActivity.this).showDialog("แจ้งเตือน", "กรุณากรอกชื่อผู้ใช้งานหรือรหัสผ่านให้ครบถ้วน", "ตกลง");
        }
    }

    private ProgressDialog pDialog;

    class LoginProcessing extends AsyncTask<Context, Integer, Integer> {
        private String username;
        private String password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("กำลังเข้าสู่ระบบ ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            username = mEmailView.getText().toString();
            password = mPasswordView.getText().toString();
        }



        @Override
        protected Integer doInBackground(Context... params) {
            int result = db.checkUser(username, password);
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            LoginBroadcast(result);
        }

        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public void LoginBroadcast(int result) {
        if (result == 0) {
            Intent i = new Intent(this, employee.class);
            startActivity(i);
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("แจ้งเตือน")
                    .setCancelable(false)
                    .setMessage("ชื่อผู้ใช้ หรือ รหัสผ่าน ไม่ถูกต้อง")
                    .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(R.drawable.icon_warning);
            alert.show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);
        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }



}