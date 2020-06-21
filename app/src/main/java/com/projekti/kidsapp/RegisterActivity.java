package com.projekti.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText txtUsername, txtEmail, txtPassword, txtConfirmPassword;
    RadioGroup txtGender;
    String gender;
    //RadioButton btnMale, btnFemale;
    RadioButton select;
    Button btnRegister;
    TextView loginLink;
    TextView txtBirthday;
    Calendar calendar;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        init();
        addData();
    }

    public void init() {
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtGender = findViewById(R.id.txtGender);
        btnRegister = findViewById(R.id.btnRegister);
        loginLink = findViewById(R.id.loginLink);
        txtBirthday = findViewById(R.id.txtBirthday);
    }

    public void addData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                String birthday = txtBirthday.getText().toString();


                //button animation
                Animation animation= AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.blink);
                btnRegister.startAnimation(animation);
                // for gender selection
                select = findViewById(txtGender.getCheckedRadioButtonId());
                gender = select.getText().toString().trim();

                // for birthday selection
                txtBirthday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        calendar = Calendar.getInstance();
                        day = calendar.get(Calendar.DAY_OF_MONTH);
                        month = calendar.get(Calendar.MONTH);
                        year = calendar.get(Calendar.YEAR);
                        month = month + 1;

                        DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month + 1;
                                txtBirthday.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the username ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the email ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the password ", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(birthday)) {
                    Toast.makeText(RegisterActivity.this, "Please select your birth date", Toast.LENGTH_SHORT).show();
                } else {
                    long data = databaseHelper.insertData(username, email, password, birthday, gender);
                    if (data > 0) {
                        FancyToast.makeText(RegisterActivity.this, "You are registered", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    } else {
                        FancyToast.makeText(RegisterActivity.this, "Registration error", FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    }
                }

            }

        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
