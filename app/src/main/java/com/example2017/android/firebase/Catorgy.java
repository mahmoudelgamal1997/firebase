package com.example2017.android.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Catorgy extends AppCompatActivity {

    RecyclerView mre;
    private DatabaseReference mdatabase;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catorgy);
        Firebase.setAndroidContext(this);



        mdatabase = FirebaseDatabase.getInstance().getReference().child("catorgy");
        mdatabase.keepSynced(true);


        mre = (RecyclerView) findViewById(R.id.view2);
        mre.setHasFixedSize(true);
        mre.setLayoutManager(new LinearLayoutManager(this));

        Retrive();


    }



    @Override
    protected void onStart() {
        super.onStart();

    }









    public void Retrive(){


        FirebaseRecyclerAdapter<Posts,Post_viewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Posts, Post_viewholder>(
                Posts.class,
                R.layout.cardview,
                Post_viewholder.class,
                mdatabase

        ) {
            @Override
            protected void populateViewHolder(Post_viewholder viewHolder, final Posts model, int position) {

                viewHolder.SetTitle((model.getCatorgy_name()));
                viewHolder.SetImage(getApplicationContext(),model.getCatorgy_image());


          viewHolder.view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  sh=getSharedPreferences("plz",Context.MODE_PRIVATE );
                  SharedPreferences.Editor  mydata=sh.edit();
                  mydata.putString( "data_catorgy",model.getCatorgy_name() );
                  mydata.commit();

                  Intent intent=new Intent(getApplicationContext(),Shop.class);
                  startActivity(intent);
              }
          });


            }
        };

        mre.setAdapter(firebaseRecyclerAdapter);

    }


    public  static  class Post_viewholder extends RecyclerView.ViewHolder {

        View view;

        public Post_viewholder(final View itemView) {
            super(itemView);

            view = itemView;

        }

        public void SetTitle(String title) {

            TextView desc = (TextView) view.findViewById(R.id.textView_desc);
            desc.setText(title);
        }


        public void SetImage(final Context cnt,final String img) {

         final    ImageView imgview = (ImageView) view.findViewById(R.id.imageView);


            // .networkPolicy(NetworkPolicy.OFFLINE)
            //to cash data
            Picasso.with(cnt).load(img).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.progress).into(imgview, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                    Picasso.with(cnt).load(img).placeholder(R.drawable.progress).into(imgview);
                }
            });
        }
        }


    }

