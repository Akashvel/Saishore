package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
public class studentchangepassword extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1;
    public static String APPID = "C37858C7-9629-41B2-FF46-1EAE09FAC400";
    public static String APIKEY = "56BBC445-EAB0-4A57-8C85-88437B9BB05C";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentchangepassword);
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.oldpassword);
        e3=(EditText)findViewById(R.id.newpassword);
        b1=(Button)findViewById(R.id.change);
        Backendless.initApp(this, APPID, APIKEY);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString().trim();
                String s2 = e2.getText().toString().trim();
                final String s3 = e3.getText().toString().trim();
                Backendless.UserService.login( s1, s2, new AsyncCallback<BackendlessUser>()
                {
                    @Override
                    public void handleResponse( BackendlessUser user )
                    {
                        user.setPassword(s3);
                        Backendless.Data.of( BackendlessUser.class ).save( user, new AsyncCallback<BackendlessUser>()
                        {
                            @Override
                            public void handleResponse( BackendlessUser backendlessUser )
                            {
                                Toast.makeText(getContext(), "PASSWORD SUCCESSFULLY CHANGED", Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void handleFault( BackendlessFault backendlessFault )
                            {
                                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                            }
                        } );
                    }
                    @Override
                    public void handleFault( BackendlessFault backendlessFault )
                    {
                        Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
                    }
                } );
            }
        });
    }
    public Context getContext() {
        return this;
    }
}
