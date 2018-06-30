package com.example2017.android.firebase;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.app.Fragment;


public class MainActivity extends AppCompatActivity {
DatabaseReference clicksOnshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE},
                1);



        clicksOnshop = FirebaseDatabase.getInstance().getReference().child("covernment");

        getFragmentManager().beginTransaction()
                .add(R.id.fragment,new Fragment_Eldalel(),"code").commit();




    }



public void start(View v) {
    //Intent intent = new Intent(this, Eldalel.class);
    //startActivity(intent);

    getFragmentManager().beginTransaction()
            .add(R.id.fragment,new Fragment_Eldalel(),"code").commit();



}

    public void using(View v) {
        Intent intent = new Intent(this, usingcard.class);
        startActivity(intent);



    }

    public void code(View v) {
      //  Intent intent = new Intent(this, CheckCode.class);
       // startActivity(intent);



        getFragmentManager().beginTransaction()
                .add(R.id.fragment,new Fragment_CheckCode(),"code").commit();


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