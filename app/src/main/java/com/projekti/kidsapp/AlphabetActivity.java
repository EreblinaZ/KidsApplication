package com.projekti.kidsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AlphabetActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2, tab3;

    PagerController pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tab1 = findViewById(R.id.tabAlphabet);
        tab2 = findViewById(R.id.tabConsonants);
        tab3 = findViewById(R.id.tabVowels);

        pagerAdapter = new PagerController(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                {
                    pagerAdapter.notifyDataSetChanged();
                }

                if(tab.getPosition()==1)
                {
                    pagerAdapter.notifyDataSetChanged();
                }

                if(tab.getPosition()==2)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}