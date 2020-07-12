package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

public class staffstudentad extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1,b2;
    public static String APPID = "C37858C7-9629-41B2-FF46-1EAE09FAC400";
    public static String APIKEY = "56BBC445-EAB0-4A57-8C85-88437B9BB05C";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffstudentad);
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.aduser);
        Backendless.initApp(this, APPID, APIKEY);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString().trim();
                String s2 = e2.getText().toString().trim();
                BackendlessUser user = new BackendlessUser();
                user.setEmail(s1);
                user.setPassword(s2);

                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(getContext(), "User has been registered", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public Context getContext() {
        return this;
    }
}
