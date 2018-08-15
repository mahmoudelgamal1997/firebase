package com.example2017.android.firebase;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
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

import java.util.Calendar;
import java.util.Map;

public class CheckCode extends AppCompatActivity {
    Spinner spinner;
    DatabaseReference check, show,daily_details;
    EditText code_edit,code_edit1,code_edit2,code_edit3,code_edit4,code_edit5,code_edit6;
    String shop_selected,data_code;
    MediaPlayer mediaPlayer;
    AlertDialog.Builder alert;
    AlertDialog alertDialog;
    String codeCollection;
    int avoidAlertDialogmutltiappear=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_code);
        Firebase.setAndroidContext(this);

        //to avoid keyboard from automatic appear
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        alert=new AlertDialog.Builder(this);
        mediaPlayer = MediaPlayer.create(this,R.raw.notify);

        code_edit = (EditText) findViewById(R.id.editText10);


        code_edit1 = (EditText) findViewById(R.id.editText10);
        code_edit2 = (EditText) findViewById(R.id.editText11);
        code_edit3 = (EditText) findViewById(R.id.editText12);
        code_edit4 = (EditText) findViewById(R.id.editText13);
        code_edit5 = (EditText) findViewById(R.id.editText14);
        code_edit6 = (EditText) findViewById(R.id.editText15);

         codeCollection=code_edit1.getText().toString().toLowerCase().trim()+
                code_edit2.getText().toString().toLowerCase().trim()+
                code_edit3.getText().toString().toLowerCase().trim()+
                code_edit4.getText().toString().toLowerCase().trim()+
                code_edit5.getText().toString().toLowerCase().trim()+
                code_edit6.getText().toString().toLowerCase().trim();


     //   Toast.makeText(getApplicationContext(),codeCollection,Toast.LENGTH_LONG).show();

        check = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-7a801.firebaseio.com/CodeValue");
        show = FirebaseDatabase.getInstance().getReference().child("codes");
        daily_details=FirebaseDatabase.getInstance().getReference().child("details").push();
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

     //to prevent dialog from appear more
        avoidAlertDialogmutltiappear=0;

       //to collect all codes in one string
        codeCollection=code_edit1.getText().toString().toLowerCase().trim()+
                code_edit2.getText().toString().toLowerCase().trim()+
                code_edit3.getText().toString().toLowerCase().trim()+
                code_edit4.getText().toString().toLowerCase().trim()+
                code_edit5.getText().toString().toLowerCase().trim()+
                code_edit6.getText().toString().toLowerCase().trim();


        if(!TextUtils.isEmpty(codeCollection.toString())){
// Search for code
  show.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.hasChild(codeCollection.toString().toLowerCase().trim()))
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


        show.child(codeCollection.toString().toLowerCase().trim()).addValueEventListener(new ValueEventListener() {
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
                String phone=map.get("phone");
                String adress=map.get("adress");



                if (avoidAlertDialogmutltiappear==0)
                    {
                        ShowMessage(data_code, person1, person2, person3, person4, person5,phone,adress);

                    }

                avoidAlertDialogmutltiappear++;



                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }





public void ShowMessage(final String message, String person1, String person2, String person3, String person4, String person5,String number,String adress){

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(codeCollection.trim());
    builder.setPositiveButton("خصم", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
        //to avoid multi press in button and discount reduntant points

                 discount(data_code,codeCollection.toString().toLowerCase().trim());
                 details(shop_selected,codeCollection.toString().toLowerCase().trim());
                 alertDialog.dismiss();

        }
    });
    String organize_message=shop_selected+" : "+message+"\n" +
            "person1: "+person1+"\n"+
            "person2: "+person2+"\n"+
            "person3: "+person3+"\n"+
            "person4: "+person4+"\n"+
            "person5: "+person5+"\n"+
            "phone number : "+ number +"\n"+
            "adress : "+adress+"\n";



    builder.setMessage(organize_message);
     alertDialog= builder.create();
     alertDialog.show();
     alertDialog.getWindow().setLayout(800, 1100);

    //Controlling width and height.
}


    public void discount(String value,String code)
    {
        //to find the balance of the code
        // if equals zero
        //no operation will be done
        try {
            int v = Integer.parseInt(value);
            if (v != 0) {
                v--;
                String s = String.valueOf(v);
                show.child(code).child(shop_selected).setValue(s);
                mediaPlayer.start();
                alert.setMessage(" لقد تم الخصم");
                alert.setCancelable(false);
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog a = alert.create();
                a.show();

            } else {
                mediaPlayer.start();
                alert.setMessage("لا تمتلك عدد زيارات كافيه");
                alert.setCancelable(true);
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog a = alert.create();
                a.show();

            }

        }catch (Exception e){


        }
    }



    public void details (String value,String code){


        daily_details.child("shop").setValue(value);
        daily_details.child("code").setValue(code);
        String b=date_time();
        daily_details.child("date").setValue(b);


    }

    public String date_time(){

        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);


        String collection=""+year+"-"+(month+1)+"-"+day +"   "+hour+":"+minute;

        return collection;
    }


//under developemant
    public void Alert(String title,String message ,String button){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title)
        .setMessage(message)
        .setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }) ;





    }





}