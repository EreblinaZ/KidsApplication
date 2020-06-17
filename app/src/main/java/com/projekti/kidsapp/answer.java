package com.projekti.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class answer extends AppCompatActivity {

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        textView1 = findViewById(R.id.answerText);

        String answertxt = getIntent().getStringExtra("answer");
        textView1.setText(answertxt);
    }
}