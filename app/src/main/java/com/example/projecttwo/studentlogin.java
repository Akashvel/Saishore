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
import com.backendless.push.DeviceRegistrationResult;

import java.util.ArrayList;
import java.util.List;

public class studentlogin extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1,b2;
    public static String APPID = "C37858C7-9629-41B2-FF46-1EAE09FAC400";
    public static String APIKEY = "56BBC445-EAB0-4A57-8C85-88437B9BB05C";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.pwd);
        e3=(EditText)findViewById(R.id.email);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.restore);
        Backendless.initApp(this, APPID, APIKEY);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString().trim();
                String s2 = e2.getText().toString().trim();
                Backendless.UserService.login(s1,s2,new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Intent intent = new Intent(getApplicationContext(),studentactivity.class);
                        startActivity(intent);
                        List<String> channels = new ArrayList<>();
                        channels.add( "default" );
                        Backendless.Messaging.registerDevice(channels, new AsyncCallback<DeviceRegistrationResult>() {
                            @Override
                            public void handleResponse(DeviceRegistrationResult response) {
                                //Toast.makeText(studentlogin.this,"DEVICE REGISTERED",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText( studentlogin.this, "Error registering " + fault.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
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
