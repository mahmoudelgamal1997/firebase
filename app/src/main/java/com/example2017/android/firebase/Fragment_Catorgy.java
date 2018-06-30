package com.example2017.android.firebase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by M7moud on 30-Jun-18.
 */
public class Fragment_Catorgy extends Fragment{

    RecyclerView mre;
    private DatabaseReference mdatabase;
    SharedPreferences sh;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


    View view3=inflater.inflate(R.layout.activity_catorgy,null);

    Firebase.setAndroidContext(getActivity());



        mdatabase = FirebaseDatabase.getInstance().getReference().child("catorgy");
        mdatabase.keepSynced(true);


        mre = (RecyclerView)view3. findViewById(R.id.view2);
        mre.setHasFixedSize(true);
        mre.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrive();


        return view3;
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
                viewHolder.SetImage(getActivity(),model.getCatorgy_image());


                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sh=getActivity().getSharedPreferences("plz", Context.MODE_PRIVATE );
                        SharedPreferences.Editor  mydata=sh.edit();
                        mydata.putString( "data_catorgy",model.getCatorgy_name() );
                        mydata.commit();

                        //to intent the fragment

                        Fragment_Shop fc=new Fragment_Shop();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment, fc)
                                .commit();
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

            final ImageView imgview = (ImageView) view.findViewById(R.id.imageView);


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





