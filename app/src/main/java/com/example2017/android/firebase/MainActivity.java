package com.example2017.android.firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView mre;
    private StorageReference s;
    private DatabaseReference mdatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);



        mdatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        s = FirebaseStorage.getInstance().getReference();




        mre = (RecyclerView) findViewById(R.id.view);
        mre.setHasFixedSize(true);
        mre.setLayoutManager(new LinearLayoutManager(this));




    }




    @Override
    protected void onStart() {
        super.onStart();

        Retrive();
    }









    public void Retrive(){


        FirebaseRecyclerAdapter<Posts,Post_viewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Posts, Post_viewholder>(
                Posts.class,
                R.layout.cardview,
                Post_viewholder.class,
                mdatabase

        ) {
            @Override
            protected void populateViewHolder(Post_viewholder viewHolder, Posts model, int position) {

                viewHolder.SetTitle((model.getTitle()));
             //   viewHolder.SetWriter(model.getWriter());
                viewHolder.SetImage(getApplicationContext(),model.getImg());


            }
        };

        mre.setAdapter(firebaseRecyclerAdapter);

    }


    public  static  class Post_viewholder extends RecyclerView.ViewHolder{

        View view;
        public Post_viewholder(View itemView) {
            super(itemView);

            view=itemView;


        }
    public void SetTitle(String title)
    {

        TextView desc=(TextView)view.findViewById(R.id.textView_desc);
        desc.setText(title);
    }


        /*
public void SetWriter(String writer)
    {
    TextView wri=(TextView)view.findViewById(R.id.textView_writer);
    wri.setText(writer);
    }
*/


        public void SetImage(Context cnt,String img )
    {

        ImageView imgview=(ImageView)view.findViewById(R.id.imageView);
        Picasso.with(cnt).load(img).into(imgview);
}


    }






}




/*
        Posts post1=new Posts();
        post1.setTitle("mahmoud");
        m.push().setValue(post1);

        Posts post2=new Posts();
        post2.setTitle("elgamal");
        m.push().setValue(post2);

*/














/*

        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                arrayList.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }}


        final Posts posts1 = new Posts();
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


        p.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String,Posts> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Posts>>() {});

                List<Posts> posts = new ArrayList<>(results.values());


                for (Posts post : posts) {

                    s.add(post.getTitle());
                    //Log.e("Post Title",));
                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
                s.add(String.valueOf(post.getTitle()));
                Toast.makeText(getApplication(), (CharSequence) s,Toast.LENGTH_LONG).show();

            }



        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
}
*/