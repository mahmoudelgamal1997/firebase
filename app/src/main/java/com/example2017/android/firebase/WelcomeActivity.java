package com.example2017.android.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends Activity {

    /** Duration of wait **/

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_welcome);

        int welcome_time = 2000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, welcome_time);



    }
}

