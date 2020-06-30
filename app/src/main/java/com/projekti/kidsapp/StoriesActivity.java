package com.projekti.kidsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoriesActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    DatabaseReference databaseReference;
    List<String> title_list, answer_list;
    ArrayAdapter<String> arrayAdapter;

    Stories stories1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        ButterKnife.bind(this);
    //    listView = findViewById(R.id.listView);

        databaseReference= FirebaseDatabase.getInstance().getReference("kidsapp");
        stories1 = new Stories();
        title_list = new ArrayList<>();
        answer_list = new ArrayList<>();

        //item layout
        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.item,title_list);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    stories1 = d.getValue(Stories.class);
                    title_list.add(stories1.getTitle());
                    answer_list.add(stories1.getAnswer());
                }
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent (StoriesActivity.this, answer.class);
                        String p = answer_list.get(position);
                        intent.putExtra("answer",p);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}