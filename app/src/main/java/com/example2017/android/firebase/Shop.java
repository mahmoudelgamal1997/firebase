package com.example2017.android.firebase;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseException;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Shop extends AppCompatActivity {
    TextView CustomAppearText;
    SharedPreferences sh;
    DatabaseReference shop,clicksOnshop;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Firebase.setAndroidContext(this);




         CustomAppearText=(TextView)findViewById(R.id.customText);
        recyclerView=(RecyclerView)findViewById(R.id.view3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        sh=getSharedPreferences("plz",Context.MODE_PRIVATE );

        shop= FirebaseDatabase.getInstance().getReference().child("catorgy").child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_city","emputy"));

        clicksOnshop=FirebaseDatabase.getInstance().getReference().child("ShopVisitors");

        shop.keepSynced(true);
        clicksOnshop.keepSynced(true);

        try {
            //to view a custom text  when data is emputy
            shop.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getChildrenCount() == 0) {

                        CustomAppearText.setText("No Item To Appear\n " + "(لا يوجد بيانات)");

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            retrieve(shop);


        }catch (FirebaseException e){
            Toast.makeText(getApplication(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
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
                    sh=getSharedPreferences("plz",Context.MODE_PRIVATE );
                    SharedPreferences.Editor  mydata=sh.edit();
                    mydata.putString( "data_shop",model.getCatorgy_name() );
                    mydata.commit();





                        //to calculate clicks

                        calculateClicks(model.getCatorgy_name());


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


        public void SetImage(final Context cnt, final String img) {

      final       ImageView imgview = (ImageView) view.findViewById(R.id.imageView);


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



    private void calculateClicks(final String name)

    {


    clicksOnshop.child(name).child("visits").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            String numberOfVisit = dataSnapshot.getValue(String.class);
            count_shop_visit(numberOfVisit, name);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    });



    }

    public void  count_shop_visit (String number,String Shop_Name){

        int x=Integer.parseInt(number);
        x++;
     //   clicksOnshop.child(Shop_Name).child(Shop_Name).setValue(Shop_Name);
       clicksOnshop.child(Shop_Name).child("visits").setValue(String.valueOf(x));

    }


}
