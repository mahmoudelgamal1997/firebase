package com.example2017.android.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class gallery extends Activity {
ImageView imageView;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageView=(ImageView)findViewById(R.id.gallery);

        sh = getSharedPreferences("plz", Context.MODE_PRIVATE);

       final String data = sh.getString("data_image", "emputy").trim();

        Picasso.with(getApplicationContext()).load(data).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.progress).into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

                Picasso.with(getApplicationContext()).load(data).placeholder(R.drawable.progress).into(imageView);
            }
        });

    }
}
