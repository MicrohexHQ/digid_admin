package in.co.ace.digid_admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText e1,e2;
    String id,pass;
    String email,password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(MainActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
                  } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };


    }


    public  void login(View view)
    {

        email="admin@admin.com";
        password="admin123";


        id=e1.getText().toString().trim();
        pass=e2.getText().toString().trim();
        if(id.equals("admin")&&pass.equals("admin123"))
        {mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Fail",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,Scan.class);
                            startActivity(i);
                        }


                        // ...
                    }
                });



        }

    }
}
