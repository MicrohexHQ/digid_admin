package in.co.ace.digid_admin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class confirm extends AppCompatActivity {
        TextView t;
    String uid;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference users = root.child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        t=(TextView)findViewById(R.id.textView);
        uid=getIntent().getStringExtra("uid");

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(uid)){
                    Toast.makeText(confirm.this, "Sucess", Toast.LENGTH_SHORT).show();
                t.setText("Verified");
                   t.setTextColor(Color.parseColor("#00ff00"));
                }
                else {

                    t.setText("Not Verified");
                   t.setTextColor(Color.parseColor("#ff0000"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }



}
