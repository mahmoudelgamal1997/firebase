package com.example2017.android.firebase;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import android.app.Fragment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
DatabaseReference clicksOnshop;
    RelativeLayout fragment;

    Button but1,but2,but3,but4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); //<< this
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);


        Firebase.setAndroidContext(this);


        but1=(Button)findViewById(R.id.button4);
        but2=(Button)findViewById(R.id.button3);
        but3=(Button)findViewById(R.id.button6);
        but4=(Button)findViewById(R.id.button5);





        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE},
                1);



        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.CALL_PHONE},
                1);


        clicksOnshop = FirebaseDatabase.getInstance().getReference().child("covernment");
        fragment=(RelativeLayout)findViewById(R.id.fragment);
        getFragmentManager().beginTransaction()
                .add(R.id.fragment,new Fragment_Eldalel(),"code").commit();




    }



@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public void start(View v) {


    but1.setBackground(getDrawable(R.drawable.button_red));
    but2.setBackground(getDrawable(R.drawable.button));
    but3.setBackground(getDrawable(R.drawable.button));
    but4.setBackground(getDrawable(R.drawable.button));

    getFragmentManager().beginTransaction()
            .add(R.id.fragment,new Fragment_Eldalel(),"key").commit();



}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void using(View v) {

        but1.setBackground(getDrawable(R.drawable.button));
        but2.setBackground(getDrawable(R.drawable.button));
        but3.setBackground(getDrawable(R.drawable.button));
        but4.setBackground(getDrawable(R.drawable.button_red));



        Intent intent = new Intent(this, usingcard.class);
        startActivity(intent);



    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void code(View v) {

        but1.setBackground(getDrawable(R.drawable.button));
        but2.setBackground(getDrawable(R.drawable.button_red));
        but3.setBackground(getDrawable(R.drawable.button));
        but4.setBackground(getDrawable(R.drawable.button));


        getFragmentManager().beginTransaction()
                .add(R.id.fragment,new Fragment_CheckCode(),"code").commit();


    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void offer(View v) {

        but1.setBackground(getDrawable(R.drawable.button));
        but2.setBackground(getDrawable(R.drawable.button));
        but3.setBackground(getDrawable(R.drawable.button_red));
        but4.setBackground(getDrawable(R.drawable.button));



        getFragmentManager().beginTransaction()
                .add(R.id.fragment,new Offers(),"offer").commit();


    }










    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                return;
            }



    }}}}