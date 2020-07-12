package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class studentrestore extends AppCompatActivity {

    EditText e1;
    Button b1;
    public static String APPID = "C37858C7-9629-41B2-FF46-1EAE09FAC400";
    public static String APIKEY = "56BBC445-EAB0-4A57-8C85-88437B9BB05C";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentrestore);
        e1=(EditText)findViewById(R.id.email);
        b1=(Button)findViewById(R.id.restore);
        Backendless.initApp(this, APPID, APIKEY);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s3 = e1.getText().toString().trim();
                Backendless.UserService.restorePassword( s3, new AsyncCallback<Void>()
                {
                    public void handleResponse( Void response )
                    {
                        Toast.makeText(getContext(),"Password Sent To Your Mail", Toast.LENGTH_LONG).show();
                        // Backendless has completed the operation - an email has been sent to the user
                    }

                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(getContext(),"Email Not Found", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public Context getContext() {
        return this;
    }
}
