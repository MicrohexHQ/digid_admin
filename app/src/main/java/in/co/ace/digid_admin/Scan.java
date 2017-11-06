package in.co.ace.digid_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class Scan extends AppCompatActivity {

    Button b;


    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        getSupportActionBar().setTitle("WELCOME");
        b=(Button)findViewById(R.id.button2);

        qrScan = new IntentIntegrator(this);


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
       {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                    Intent i=new Intent(Scan.this,confirm.class);
                    String s=result.getContents();

                    i.putExtra("uid",s);

                    startActivity(i);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



public void doit(View view)
{

    qrScan.initiateScan();

}

}
