package com.example2017.android.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CheckCode extends AppCompatActivity {
    Spinner spinner;
    DatabaseReference check;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_code);
        Firebase.setAndroidContext(this);



        editText=(EditText)findViewById(R.id.editText);
        check=FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-7a801.firebaseio.com/CodeValue");
        spinner=(Spinner)findViewById(R.id.spinner);


        FirebaseListAdapter<String> firebaseListAdapter=new FirebaseListAdapter<String>(
                this,
                String.class,
                R.layout.text_style,
                check
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

                TextView textView=(TextView)v.findViewById(R.id.textView);
                textView.setText(model);

            }

        };
spinner.setAdapter(firebaseListAdapter);
    }

}
