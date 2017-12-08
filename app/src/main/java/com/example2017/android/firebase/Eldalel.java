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
                Toast.makeText(getApplicationContext(),model.getCatorgy_name(),Toast.LENGTH_LONG);

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


        public void SetImage(Context cnt, String img) {

            ImageView imgview = (ImageView) view.findViewById(R.id.imageView);
            Picasso.with(cnt).load(img).into(imgview);
        }


    }  }

/*

    public class FirebaseViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mPostTitle;
        public TextView mPostDesc;

        public FirebaseViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.image_recyclerview);
            mPostTitle = (TextView) itemView.findViewById(R.id.tv_title_recyclerview_item);
            mPostDesc = (TextView) itemView.findViewById(R.id.tv_desc_recyclerview_item);

            //listener set on ENTIRE ROW, you may set on individual components within a row.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, getAdapterPosition());

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mClickListener.onItemLongClick(v, getAdapterPosition());
                    return true;
                }
            });

        }
        private FirebaseViewHolder.ClickListener mClickListener;

        //Interface to send callbacks...
        public interface ClickListener{
            public void onItemClick(View view, int position);
            public void onItemLongClick(View view, int position);
        }

        public void setOnClickListener(FirebaseViewHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }
    }

    Creating the FirebaseRecyclerAdapter.

            FirebaseRecyclerAdapter<Post, FirebaseViewHolder> adapter = new   FirebaseRecyclerAdapter<Post, FirebaseViewHolder>(
            Post.class,
            R.layout.recyclerview_list_item,
            FirebaseViewHolder.class,
            databaseReference

    ) {
        @Override
        protected void populateViewHolder(FirebaseViewHolder viewHolder, Post model, int position) {

            Picasso.with(getActivity())
                    .load(model.image_url)
                    .into(viewHolder.mImageView);
            Log.v(TAG, model.image_url);
            //viewHolder.mImageView.setImageResource(R.mipmap.ic_launcher);
            viewHolder.mPostTitle.setText(model.title);
            viewHolder.mPostDesc.setText(model.desc);
        }

        @Override
        public FirebaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FirebaseViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
            viewHolder.setOnClickListener(new FirebaseViewHolder.ClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getApplicationContext(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemLongClick(View view, int position) {
                    Toast.makeText(getApplicationContext(), "Item long clicked at " + position, Toast.LENGTH_SHORT).show();
                }
            });
            return viewHolder;
        }





*/
