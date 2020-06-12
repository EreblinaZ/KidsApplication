package com.projekti.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button btnAlphabet;
    private Button btnNumbers;
    private Button btnColors;
    private Button btnStories;
    private Button btnAboutUs;
    private Button btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnAlphabet = findViewById(R.id.btnAlphabet);
        btnNumbers = findViewById(R.id.btnNumbers);
        btnColors = findViewById(R.id.btnColors);
        btnStories = findViewById(R.id.btnStories);
        btnAboutUs = findViewById(R.id.btnAboutUs);
        btnHelp = findViewById(R.id.btnHelp);

        btnAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlphabetActivity();
            }
        });

        btnNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNumbersActivity();
            }
        });

        btnColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorsActivity();
            }
        });

        btnStories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoriesActivity();
            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutUsActivity();
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpActivity();
            }
        });
    }


    public void openAlphabetActivity(){
        Intent intent = new Intent(this, AlphabetActivity.class);
        startActivity(intent);
    }

    public void openNumbersActivity(){
        Intent intent = new Intent(this,NumbersActivity.class);
        startActivity(intent);
    }

    public void openColorsActivity(){
        Intent intent = new Intent(this,ColorsActivity.class);
        startActivity(intent);
    }

    public void openStoriesActivity(){
        Intent intent = new Intent(this,StoriesActivity.class);
        startActivity(intent);
    }

    public void openAboutUsActivity(){
        Intent intent = new Intent(this,AboutUsActivity.class);
        startActivity(intent);
    }

    public void openHelpActivity(){
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
    }

}
