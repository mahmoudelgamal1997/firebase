package com.example2017.android.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class shop_details extends AppCompatActivity {
TextView title,home,number,details;
    SharedPreferences sh;
    DatabaseReference def;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        Firebase.setAndroidContext(this);

        imageView = (ImageView) findViewById(R.id.imageView3);
        title = (TextView) findViewById(R.id.title_text);
        home = (TextView) findViewById(R.id.home_text);
        number = (TextView) findViewById(R.id.mobile_text);
        details = (TextView) findViewById(R.id.details_text);


        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);
        def= FirebaseDatabase.getInstance().getReference().child("catorgy").child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_city","emputy")).child(sh.getString( "data_shop","emputy"));



def.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {


        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
        Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );


        String details_data=map.get("shop_details");
        String mobile_data=map.get("shop_mobile");
        String home_data=map.get("shop_home");
        String img=map.get("catorgy_image");
        String name=map.get("catorgy_name");



        SetImage(getApplicationContext(),img);
        details.setText(details_data);
        home.setText(home_data);
        number.setText(mobile_data);
        title.setText(name);

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

    }





    public void SetImage(Context cnt, String img) {


        ImageView imgview = (ImageView)findViewById(R.id.imageView3);
        Picasso.with(cnt).load(img).into(imgview);
    }




}
