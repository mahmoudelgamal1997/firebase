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
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Shop extends AppCompatActivity {

    SharedPreferences sh;
    DatabaseReference shop;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Firebase.setAndroidContext(this);





        recyclerView=(RecyclerView)findViewById(R.id.view3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        sh=getSharedPreferences("plz",Context.MODE_PRIVATE );

        shop= FirebaseDatabase.getInstance().getReference().child("catorgy").child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_city","emputy"));

        retrieve(shop);



    }




private void retrieve(DatabaseReference data){


    FirebaseRecyclerAdapter<Posts,Post_shop_viewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Posts, Post_shop_viewholder>(
            Posts.class,
            R.layout.cardview,
            Post_shop_viewholder.class,
            data


    ) {
        @Override
        protected void populateViewHolder(Post_shop_viewholder viewHolder, final Posts model, int position) {
            viewHolder.SetTitle(model.getCatorgy_name());
            viewHolder.SetImage(getApplicationContext(),model.getCatorgy_image());

            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(getApplicationContext(),model.getCatorgy_name(),Toast.LENGTH_LONG).show();
                    sh=getSharedPreferences("plz",Context.MODE_PRIVATE );
                    SharedPreferences.Editor  mydata=sh.edit();
                    mydata.putString( "data_shop",model.getCatorgy_name() );
                    mydata.commit();

                    Intent intent=new Intent(getApplicationContext(),shop_details.class);
                    startActivity(intent);


                }
            });
        }
    };


recyclerView.setAdapter(firebaseRecyclerAdapter);
}





    public static class Post_shop_viewholder extends RecyclerView.ViewHolder{
        android.view.View view;


        public Post_shop_viewholder(final android.view.View itemView) {
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

    }



}
