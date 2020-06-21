package com.projekti.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {
    private Button btnAlphabet;
    private Button btnNumbers;
    private Button btnColors;
    private Button btnStories;
    private Button btnAboutUs;
    private Button btnHelp;
    LinearLayout alphabetLayout;

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


     }


    public void ActivityBtn(View view) {
        if (view == findViewById(R.id.btnAlphabet)) {
            startActivity(new Intent(this,AlphabetActivity.class));
            Animatoo.animateSpin(this);

        }
        if(view==findViewById(R.id.btnNumbers)){
            startActivity(new Intent(this, NumbersActivity.class));
            Animatoo.animateDiagonal(this);
        }
        if(view==findViewById(R.id.btnColors)){
            startActivity(new Intent(this, ColorsActivity.class));
            Animatoo.animateSwipeLeft(this);
        }
        if(view==findViewById(R.id.btnStories)) {
            startActivity(new Intent(this, StoriesActivity.class));
            Animatoo.animateSlideDown(this);
        }

        if(view==findViewById(R.id.btnAboutUs)){
            startActivity(new Intent(this, AboutUsActivity.class));
            Animatoo.animateFade(this);
        }
        if(view==findViewById(R.id.btnHelp)){
            startActivity(new Intent(this, HelpActivity.class));
            Animatoo.animateZoom(this);
        }




    }
}
