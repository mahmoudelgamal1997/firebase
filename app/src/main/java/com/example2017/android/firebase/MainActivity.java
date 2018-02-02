package com.example2017.android.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }



public void start(View v) {
    Intent intent = new Intent(this, Eldalel.class);
    startActivity(intent);

}


    public void code(View v) {
        Intent intent = new Intent(this, CheckCode.class);
        startActivity(intent);


    }


}

















/*

        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                arrayList.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }}


        final Posts posts1 = new Posts();
        posts1.setTitle("film");
        posts1.setContent("i love action");
        posts1.setWriter("omar");

        p.push().setValue(posts1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplication(), "success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplication(), "failed", Toast.LENGTH_SHORT).show();

                }
            }
        });


        p.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String,Posts> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Posts>>() {});

                List<Posts> posts = new ArrayList<>(results.values());


                for (Posts post : posts) {

                    s.add(post.getTitle());
                    //Log.e("Post Title",));
                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }







public void but(View view){
    p.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            HashMap<String,Posts> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Posts>>() {});

            List<Posts> posts = new ArrayList<>(results.values());

            for (Posts post : posts) {
                Log.e("Post Title", post.getTitle());
                s.add(String.valueOf(post.getTitle()));
                Toast.makeText(getApplication(), (CharSequence) s,Toast.LENGTH_LONG).show();

            }



        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
}
*/