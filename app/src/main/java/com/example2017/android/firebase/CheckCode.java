package com.example2017.android.firebase;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class CheckCode extends AppCompatActivity {
    Spinner spinner;
    DatabaseReference check, show;
    EditText code_edit;
    String shop_selected,data_code;
    MediaPlayer mediaPlayer;
    AlertDialog.Builder alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_code);
        Firebase.setAndroidContext(this);


        alert=new AlertDialog.Builder(this);
        mediaPlayer = MediaPlayer.create(this,R.raw.notify);

        code_edit = (EditText) findViewById(R.id.editText);
        check = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-7a801.firebaseio.com/CodeValue");
        show = FirebaseDatabase.getInstance().getReference().child("codes");
               // .child(code_edit.getText().toString().toLowerCase().trim());

        spinner = (Spinner) findViewById(R.id.spinner);


        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                R.layout.text_style,
                check
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

                TextView textView = (TextView) v.findViewById(R.id.textView);
                textView.setText(model);
                shop_selected = model;

            }

        };
        spinner.setAdapter(firebaseListAdapter);


    }

    public void but(View v) {

if(!TextUtils.isEmpty(code_edit.getText().toString())){
// Search for code
  show.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.hasChild(code_edit.getText().toString().toLowerCase().trim()))
        {
            retrive_single();

        }else{

            mediaPlayer.start();
            alert.setMessage("this code isn't correct");
            alert.setCancelable(true);
            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog a=alert.create();
            a.show();
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

    }else{
    Toast.makeText(getApplicationContext(),"Enter Your Code",Toast.LENGTH_LONG).show();
    }




    }




    public void retrive_single() {


        show.child(code_edit.getText().toString().toLowerCase().trim()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );

                data_code = map.get(shop_selected);

                String person1=map.get("person1");
                String person2=map.get("person2");
                String person3=map.get("person3");
                String person4=map.get("person4");
                String person5=map.get("person5");

                ShowMessage(data_code,person1,person2,person3,person4,person5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }





public void ShowMessage(String message,String person1,String person2,String person3,String person4,String person5){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(code_edit.getText().toString().trim());

    String organize_message=shop_selected+" : "+message+"\n" +
            "person1: "+person1+"\n"+
            "person2: "+person2+"\n"+
            "person3: "+person3+"\n"+
            "person4: "+person4+"\n"+
            "person5: "+person5+"\n";

    builder.setMessage(organize_message);
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
    alertDialog.getWindow().setLayout(1000, 1500); //Controlling width and height.

}

}