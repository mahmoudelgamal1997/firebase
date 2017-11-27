package com.example2017.android.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference p;
    Firebase mfb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
         p = database.getReference("my_name");


        Posts posts1 = new Posts();
        posts1.setTitle("film");
        posts1.setContent("i love action");
        posts1.setWriter("omar");

        p.push().setValue(posts1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplication(), "success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplication(), "failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


public void but(View view){
    p.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            HashMap<String,Posts> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Posts>>() {});

            List<Posts> posts = new ArrayList<>(results.values());

            for (Posts post : posts) {
                Log.e("Post Title", post.getTitle());
                Toast.makeText(getApplication(), String.valueOf(post.getTitle()),Toast.LENGTH_LONG).show();
            }



        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
}
