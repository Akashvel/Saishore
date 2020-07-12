package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
public class masterlogin extends AppCompatActivity {
    public static String APPID = "040F82AA-C3A8-7FF4-FFDD-52AEFD69A900";
    public static String APIKEY = "0E8CF9F5-6798-4D31-842D-99F47ADEC934";
    EditText e1,e2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterlogin);
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.pwd);
        b1=(Button)findViewById(R.id.loginbutton);
        Backendless.initApp(this, APPID, APIKEY);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1 = e1.getText().toString().trim();
                String s2 = e2.getText().toString().trim();
                Backendless.UserService.login(s1,s2,new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Intent intent = new Intent(getApplicationContext(),masterassignment.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "LOGGED IN", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getContext(),"LOGIN FAILED", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public Context getContext() {
        return this;
    }
}
