package com.example2017.android.firebase;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Eldalel extends AppCompatActivity {
    RecyclerView mre;
    private StorageReference s;
    private DatabaseReference mdatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eldalel);
        Firebase.setAndroidContext(this);



        mdatabase = FirebaseDatabase.getInstance().getReference().child("City");
        s = FirebaseStorage.getInstance().getReference();
        mdatabase.keepSynced(true);



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
                viewHolder.SetImage(getApplicationContext(),model.getImg());
           //     Toast.makeText(getApplicationContext(),model.getCatorgy_name(),Toast.LENGTH_LONG);

            }
        };

        mre.setAdapter(firebaseRecyclerAdapter);

    }


    public  static  class Post_viewholder extends RecyclerView.ViewHolder {

        View view;

        public Post_viewholder(View itemView) {
            super(itemView);

            view = itemView;


        }

        public void SetTitle(String title) {

            TextView desc = (TextView) view.findViewById(R.id.textView_desc);
            desc.setText(title);
        }


        public void SetImage(final Context cnt, final String img) {

            final ImageView imgview = (ImageView) view.findViewById(R.id.imageView);
            Picasso.with(cnt).load(img).networkPolicy(NetworkPolicy.OFFLINE).into(imgview, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(cnt).load(img).into(imgview);
                }
            });



        }



    }  }

