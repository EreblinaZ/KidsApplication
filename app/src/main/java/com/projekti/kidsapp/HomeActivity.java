package com.projekti.kidsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private Button btnAnimals;
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
        btnAnimals = findViewById(R.id.btnAnimals);

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

        if(view==findViewById(R.id.btnAnimals)){
            startActivity(new Intent(this, AnimalsActivity.class));
            Animatoo.animateZoom(this);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_logout:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_help:
                Intent intent1 = new Intent(this,HelpActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_exit:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
        }
        return super.onOptionsItemSelected(item);
    }
}
