package com.projekti.kidsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnLogin;
    TextView registerLink;
    DatabaseHelper databaseHelper;

    TextView tvProgress;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean saveLogin;
    CheckBox saveLoginCheckBox;

    private  final int REQUEST_PERMISSION_CODE = 1;

    private final String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        tvProgress = findViewById(R.id.tvProgress);

        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        saveLoginCheckBox = findViewById(R.id.checkBoxRememberMe);
        editor = sharedPreferences.edit();


        registerLink = findViewById(R.id.registerLink);


        databaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

                AsyncTaskWork asyncTaskWork = new AsyncTaskWork();
                asyncTaskWork.execute();

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                boolean res = databaseHelper.checkUser(email, password);


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Please enter the email ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter the password ", Toast.LENGTH_SHORT).show();
                } else if (res) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("data", email);
                    startActivity(intent);
                    finish();
                } else {
                    FancyToast.makeText(LoginActivity.this, "Not Registered", FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                }

                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.blink);
                btnLogin.startAnimation(animation);
            }
        });


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        // Shared Preferences
        saveLogin = sharedPreferences.getBoolean("save login", true);
        if(saveLogin){
            txtEmail.setText(sharedPreferences.getString("email",null));
            txtPassword.setText(sharedPreferences.getString("password",null));
        }



        // Permissions
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            writeToDisk();
        }
        else {
            if(Build.VERSION.SDK_INT >= 23) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_CODE);
            }
        }

    }

    private void writeToDisk() {
    }


    public void login(){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(saveLoginCheckBox.isChecked()){
            editor.putBoolean("savelogin", true);
            editor.putString("email", email);
            editor.putString("password",password);
            editor.commit();
        }
    }

    private class AsyncTaskWork extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute(){
            tvProgress.setText("0");
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (int i=1; i<=5; i++){
                waitOneSecond();
                publishProgress(i);
            }
            return "Welcome to KidsApp";
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            tvProgress.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(String result){
            tvProgress.setText(result);
        }

    }

    private void waitOneSecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){

        }

    }
}
