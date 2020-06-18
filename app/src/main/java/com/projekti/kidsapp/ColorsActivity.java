package com.projekti.kidsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ColorsActivity extends AppCompatActivity{


    ListView listView;
    String mTitle[]={"Red","Orange","Yellow","Green", "Blue","Beige","Pink","Grey","Brown","Black","White"};
    int images[]={R.drawable.app_red, R.drawable.app_orange, R.drawable.app_yellow, R.drawable.app_green, R.drawable.app_blue, R.drawable.app_beige,
            R.drawable.app_pink, R.drawable.app_grey, R.drawable.app_brown, R.drawable.app_black, R.drawable.app_white};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        listView=findViewById(R.id.listView);

        MyAdapter adapter=new MyAdapter(this, mTitle, images);
        listView.setAdapter(adapter);

    }
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        int rImages[];
        MyAdapter (Context c,String title[], int img[]){
            super(c, R.layout.row, R.id.textView, title);
            this.context=c;
            this.rTitle=title;
            this.rImages=img;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.row, parent,false);
            ImageView images=row.findViewById(R.id.image);
            TextView myTitle=row.findViewById(R.id.textView);

            images.setImageResource(rImages[position]);
            myTitle.setText(rTitle[position]);



            return row;
        }




    }
}