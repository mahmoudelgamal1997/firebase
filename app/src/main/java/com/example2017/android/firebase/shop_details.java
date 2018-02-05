package com.example2017.android.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
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

import java.io.IOException;
import java.util.Map;

public class shop_details extends AppCompatActivity {
    TextView title,home,number,details;
    SharedPreferences sh;
    DatabaseReference def,calc;
    ImageView imageView,imageViewfacebook,imageViewInstgram,imageViewTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        Firebase.setAndroidContext(this);

        imageView = (ImageView) findViewById(R.id.imageView3);

        imageViewfacebook=(ImageView)findViewById(R.id.imageView7);
        imageViewTwitter =(ImageView)findViewById(R.id.imageView8);
        imageViewInstgram=(ImageView)findViewById(R.id.imageView9);





        title = (TextView) findViewById(R.id.title_text);
        home = (TextView) findViewById(R.id.home_text);
        number = (TextView) findViewById(R.id.mobile_text);
        details = (TextView) findViewById(R.id.details_text);

        calc=FirebaseDatabase.getInstance().getReference().child("covernment");

        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);
        def= FirebaseDatabase.getInstance().getReference().child("catorgy").child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_catorgy","emputy")).child( sh.getString( "data_city","emputy")).child(sh.getString( "data_shop","emputy"));





def.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {


        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
        Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );


        String details_data=map.get("shop_details");

        String mobile_data =map.get("shop_mobile");
        String mobile_data2=map.get("shop_mobile2");
        String mobile_data3=map.get("shop_mobile3");
        String mobile_data4=map.get("shop_mobile4");

        String CollectionMobileNumber=mobile_data+"\n"+mobile_data2+"\n"+mobile_data3+"\n"+mobile_data4;

        String home_data=map.get("shop_home");
        String home_data2=map.get("shop_home2");
        String home_data3=map.get("shop_home3");

        String CollectionHomeNumber =home_data+"\n"+home_data2+"\n"+home_data3;

        String img=map.get("catorgy_image");

        String name=map.get("catorgy_name");

        final String FacebookLink=map.get("Facebook");
        final String InstgramLink=map.get("Instgram");
        final String TwitterLink=map.get("Twitter");



        SetImage(getApplicationContext(),img);
        details.setText(details_data);
        home.setText(CollectionHomeNumber);
        number.setText(CollectionMobileNumber);
        title.setText(name);

        imageViewfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                try{
                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse(FacebookLink));
                startActivity(link);
                }catch (Exception e)
                {
                    Toast.makeText(getApplication(),"لا يوجد فيس بوك لهذا المحل ",Toast.LENGTH_LONG).show();
                }
            }
        });


        imageViewTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
try {
    Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse(TwitterLink));
    startActivity(link);
}catch (Exception e)
{
    Toast.makeText(getApplication(),"لا يوجد تويتر لهذا المحل ",Toast.LENGTH_LONG).show();
}
            }
        });

        imageViewInstgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {

                try {

                    Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse(InstgramLink));
                    startActivity(link);
                }catch (Exception e)
                {
                    Toast.makeText(getApplication(),"لا يوجد انستجرام لهذا المحل ",Toast.LENGTH_LONG).show();
                }



            }
        });


    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

    }





    public void SetImage(Context cnt, String img) {


        ImageView imgview = (ImageView)findViewById(R.id.imageView3);
        Picasso.with(cnt).load(img).placeholder(R.drawable.progress).into(imgview);
    }




}
