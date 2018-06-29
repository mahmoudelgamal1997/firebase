
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
import com.firebase.client.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Map;

public class shop_details extends AppCompatActivity {
    TextView title, home, number, details, whats;
    SharedPreferences sh;
    public DatabaseReference def, calc;
    ImageView imageView, imageViewfacebook, imageViewInstgram, imageViewTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        Firebase.setAndroidContext(this);

        imageView = (ImageView) findViewById(R.id.imageView3);

        imageViewfacebook = (ImageView) findViewById(R.id.imageView7);
        imageViewTwitter = (ImageView) findViewById(R.id.imageView8);
        imageViewInstgram = (ImageView) findViewById(R.id.imageView9);


        title = (TextView) findViewById(R.id.title_text);
        home = (TextView) findViewById(R.id.home_text);
        number = (TextView) findViewById(R.id.mobile_text);
        details = (TextView) findViewById(R.id.details_text);
        whats = (TextView) findViewById(R.id.whats);
        calc = FirebaseDatabase.getInstance().getReference().child("covernment");


        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);





        String catorgy_name=sh.getString("data_catorgy", "emputy").trim();
        String city_name=sh.getString("data_city", "emputy").trim();
        String shop_name=sh.getString("data_shop", "emputy").trim();
/*
        def = FirebaseDatabase.getInstance().getReference().child("catorgy")
                .child((sh.getString("data_catorgy", "emputy").trim()))
                .child((sh.getString("data_catorgy", "emputy").trim()))
                .child(sh.getString("data_city", "emputy").trim())
                .child(sh.getString("data_shop", "emputy").trim());
*/

        String url=("https://fireapp-7a801.firebaseio.com/catorgy/"+catorgy_name+ "/"+catorgy_name+"/"+city_name+"/"+shop_name).trim();
        def = FirebaseDatabase.getInstance().getReferenceFromUrl(url);

            def.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {
                    };
                    Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator);


                    String details_data = map.get("shop_details");
                    String mobile_data = map.get("shop_mobile");
                    String mobile_data2 = map.get("shop_mobile2");
                    String mobile_data3 = map.get("shop_mobile3");
                    String mobile_data4 = map.get("shop_mobile4");

                    String CollectionMobileNumber = mobile_data + "\n" + mobile_data2 + "\n" + mobile_data3 + "\n" + mobile_data4;

                    String home_data = map.get("shop_home");
                    String home_data2 = map.get("shop_home2");
                    String home_data3 = map.get("shop_home3");

                    String CollectionHomeNumber = home_data + "\n" + home_data2 + "\n" + home_data3;

                    final String img = map.get("catorgy_image");

                    final String name = map.get("catorgy_name");

                    final String FacebookLink = map.get("Facebook");
                    final String WhatsLink = map.get("Instgram");
                    final String TwitterLink = map.get("Twitter");


                    if (details_data == null) {
                        details_data = "";
                    }

                    SetImage(getApplicationContext(), img);
                    details.setText(details_data);
                    home.setText(CollectionHomeNumber);
                    number.setText(CollectionMobileNumber);
                    title.setText(name);
                    whats.setText(WhatsLink);

                    imageViewfacebook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse(FacebookLink));
                                startActivity(link);
                            } catch (Exception e) {
                                Toast.makeText(getApplication(), "لا يوجد فيس بوك لهذا المحل ", Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                    imageViewTwitter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse(TwitterLink));
                                startActivity(link);
                            } catch (Exception e) {
                                Toast.makeText(getApplication(), "لا يوجد تويتر لهذا المحل ", Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplication(), databaseError.getMessage().toString(), Toast.LENGTH_LONG).show();

                }
            });


    }


    public void SetImage(final Context cnt, final String img) {


        final ImageView imgview = (ImageView) findViewById(R.id.imageView3);

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





