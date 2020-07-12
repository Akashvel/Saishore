package com.example.projecttwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.push.DeviceRegistrationResult;
public class masterassignment extends AppCompatActivity {

    EditText e1,e2,about,des;
    Button b1,postb;
    FirebaseAuth mfirebaseAuth;
    DatabaseReference databasepublish;
    private static String APPID = "C37858C7-9629-41B2-FF46-1EAE09FAC400";
    private static String APIKEY = "56BBC445-EAB0-4A57-8C85-88437B9BB05C";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterassignment);
        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.pwd);
        about=(EditText)findViewById(R.id.about);
        postb=(Button)findViewById(R.id.postbutton);
        des=(EditText)findViewById(R.id.des);
        Backendless.initApp(this, APPID, APIKEY);
        mfirebaseAuth=FirebaseAuth.getInstance();
        b1 = (Button)findViewById(R.id.adbutton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aduser();
            }
        });
        postb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adpost();
            }
        });
    }
    public void aduser(){
        String s1 = e1.getText().toString().trim();
        String s2 = e2.getText().toString().trim();
        mfirebaseAuth.createUserWithEmailAndPassword(s1,s2)
                .addOnCompleteListener(masterassignment.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(masterassignment.this,"USER ADDED",Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(masterassignment.this, "USER ADDED FAILED",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void adpost(){
        databasepublish = FirebaseDatabase.getInstance().getReference("general");
        String s1 = about.getText().toString().trim();
        String s2 = des.getText().toString().trim();

        String id = databasepublish.push().getKey();

        post pb = new post(id,s1,s2);

        databasepublish.child(id).setValue(pb);
        Toast.makeText(this,"PUBLISHED",Toast.LENGTH_LONG).show();
        //Backendless.Messaging.pushWithTemplate("checknotify");
        Backendless.initApp(this, APPID, APIKEY);
        PublishOptions publishOptions = new PublishOptions();
        publishOptions.putHeader("android-content-title","SAI SHORE");
        Backendless.Messaging.publish("default", s2, publishOptions, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus response) {
                Toast.makeText(masterassignment.this,"NOTIFIED",Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(masterassignment.this,"NOT",Toast.LENGTH_LONG).show();
            }
        });
    }
}
