
package com.example2017.android.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
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

import java.util.ArrayList;
import java.util.Map;

public class shop_details extends AppCompatActivity {
    TextView title, home, number, adress, whats;
    SharedPreferences sh,sh2;
    public DatabaseReference def, calc, photos,offers,branches,shop2;
    ImageView imageView, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, imageViewfacebook, imageViewInstgram, imageViewTwitter;
    CardView card_whats;
    Button maps;
    Spinner spinner_branch;
    String shop_selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        Firebase.setAndroidContext(this);



        final Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Flower Power Personal Use.ttf");


        imageView = (ImageView) findViewById(R.id.imageView_shop);

        img1 = (ImageView) findViewById(R.id.imageView11);
        img2 = (ImageView) findViewById(R.id.imageView12);
        img3 = (ImageView) findViewById(R.id.imageView13);
        img4 = (ImageView) findViewById(R.id.imageView14);
        img5 = (ImageView) findViewById(R.id.imageView15);
        img6 = (ImageView) findViewById(R.id.imageView16);
        img7 = (ImageView) findViewById(R.id.imageView17);
        img8 = (ImageView) findViewById(R.id.imageView18);
        img9 = (ImageView) findViewById(R.id.imageView19);
        img10 = (ImageView) findViewById(R.id.imageView20);
        img11 = (ImageView) findViewById(R.id.imageView21);
        img12 = (ImageView) findViewById(R.id.imageView22);

        spinner_branch=(Spinner)findViewById(R.id.spinner_branch);

        card_whats = (CardView) findViewById(R.id.card_view_mobile);


        imageViewfacebook = (ImageView) findViewById(R.id.imageView_facebook);
        imageViewInstgram = (ImageView) findViewById(R.id.imageView_instgram);
        number = (TextView) findViewById(R.id.id_mobile);


        adress = (TextView) findViewById(R.id.id_Shop_adress);
        calc = FirebaseDatabase.getInstance().getReference().child("covernment");


        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);
        sh2 = getSharedPreferences("plz2", Context.MODE_PRIVATE);


        String catorgy_name = sh.getString("data_catorgy", "emputy").trim();
        String city_name = sh.getString("data_city", "emputy").trim();
        final String shop_name = sh.getString("data_shop", "emputy").trim();
        String password = sh2.getString("password","emputy");

        if (String.valueOf(password).equals("offer"))
        {
            def=FirebaseDatabase.getInstance().getReference().child("offers");
        }else {
            def = FirebaseDatabase.getInstance().getReference().child("catorgy")
                    .child(catorgy_name)
                    .child(catorgy_name)
                    .child(city_name)
                    .child(shop_name);

            photos = def.child("photos");
            branches=FirebaseDatabase.getInstance().getReference().child("branches").child(shop_name);



        }


        //  String url=("https://fireapp-7a801.firebaseio.com/catorgy/"+catorgy_name+ "/"+city_name+"/"+catorgy_name+"/"+shop_name).trim();

        // String formatedString =  String.format("%1$s", url);
        //   def = FirebaseDatabase.getInstance().getReferenceFromUrl(formatedString);


        FirebaseListAdapter<String> firebaseListAdapter=new FirebaseListAdapter<String>(
                this,
                String.class,
                R.layout.text_style,
                branches
        ) {
            @Override
            protected void populateView(View v, String model, int position) {


                TextView textView = (TextView) v.findViewById(R.id.textView);
                textView.setText(model);
                shop_selected=model;
                shop2=def.child(shop_selected);
                prepare();
            }
        };





        spinner_branch.setAdapter(firebaseListAdapter);








    }



    public void prepare(){


        if (! shop2.equals(null)){

            shop2.addValueEventListener(new ValueEventListener() {

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

                    String CollectionMobileNumber = "";
                    if (mobile_data != null) {
                        CollectionMobileNumber = mobile_data;

                    }

                    if (mobile_data2 != null) {
                        CollectionMobileNumber += "\n" + mobile_data2;
                    }
                    if (mobile_data3 != null) {
                        CollectionMobileNumber += "\n" + mobile_data3;
                    }

                    if (mobile_data4 != null) {
                        CollectionMobileNumber += "\n" + mobile_data4;
                    }


                    String home_data = map.get("shop_home");
                    String home_data2 = map.get("shop_home2");
                    String home_data3 = map.get("shop_home3");


                    String latidue = map.get("latitude");
                    String longtude = map.get("longtude");

                    SharedPreferences.Editor  mydata=sh.edit();
                    mydata.putString( "latitude",latidue);
                    mydata.putString( "latitude",longtude);
                    mydata.commit();


                    String CollectionHomeNumber = "";
                    if (home_data != null) {
                        CollectionHomeNumber = home_data;

                    }

                    if (home_data2 != null) {
                        CollectionHomeNumber += "\n" + home_data2;
                    }
                    if (home_data2 != null) {
                        CollectionHomeNumber += "\n" + home_data3;
                    }


                    final String img = map.get("catorgy_image");

                    final String name = map.get("catorgy_name");

                    final String FacebookLink = map.get("Facebook");
                    final String WhatsLink = map.get("Instgram");


/*
                if (WhatsLink != null) {
                    whats.setText(WhatsLink);
                } else {

                    card_whats.setVisibility(View.GONE);
                }
*/

                    final String img1 = map.get("img1");
                    final String img2 = map.get("img2");
                    final String img3 = map.get("img3");
                    final String img4 = map.get("img4");
                    final String img5 = map.get("img5");
                    final String img6 = map.get("img6");
                    final String img7 = map.get("img7");
                    final String img8 = map.get("img8");
                    final String img9 = map.get("img9");
                    final String img10 = map.get("img10");
                    final String img11 = map.get("img11");
                    final String img12 = map.get("img12");




                    if (img1 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView11");
                    }

                    if (img2 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView12");
                    }

                    if (img3 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView13");
                    }

                    if (img4 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView14");
                    }

                    if (img5 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView15");
                    }

                    if (img6 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView16");
                    }

                    if (img7 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView17");
                    }

                    if (img8 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView18");
                    }
                    if (img9 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView19");
                    }
                    if (img10 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView20");
                    }
                    if (img11 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView21");
                    }
                    if (img12 != null) {

                        SetCustomImage(getApplicationContext(), img1, "imageView22");
                    }


                    if (details_data == null) {
                        details_data = "";
                    }







                    SetImage(getApplicationContext(), img);
                    adress.setText(details_data);
                    //    home.setText(CollectionHomeNumber);
                    number.setText(CollectionMobileNumber);

//                title.setTypeface(custom_font);
                    //              title.setText(name);
                    //   whats.setText(WhatsLink);

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





                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                 //   Toast.makeText(getApplication(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

                }
            });


        }



    }


    public void SetImage(final Context cnt, final String img) {


        final ImageView imgview = (ImageView) findViewById(R.id.imageView_shop);

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


    public void SetCustomImage(final Context cnt, final String img, String id) {

        int IdResource = cnt.getResources().getIdentifier(id, "id", cnt.getPackageName());
        final ImageView imgview = (ImageView) findViewById(IdResource);

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



    public String getShop_selected(String shop_selected){

        return shop_selected;
    }


    @Override
    protected void onStart() {
        super.onStart();



    }
}










