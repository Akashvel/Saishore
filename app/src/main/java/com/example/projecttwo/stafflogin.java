package com.example.projecttwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class stafflogin extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    RadioGroup rg;
    RadioButton rb1,rb2;
    FirebaseAuth mfirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stafflogin);
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.pwd);
        rg=(RadioGroup)findViewById(R.id.rg);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);

        mfirebaseAuth=FirebaseAuth.getInstance();
        b1=(Button)findViewById(R.id.loginbutton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email = e1.getText().toString().trim();
                final String Password = e2.getText().toString().trim();
                if (Email.equals("") || Password.equals("")) {
                    Toast.makeText(stafflogin.this, "Fill The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    mfirebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(stafflogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            int selectedid = rg.getCheckedRadioButtonId();
                            if (selectedid != -1) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(stafflogin.this, "ERROR OCCURED", Toast.LENGTH_SHORT).show();
                                } else {
                                    RadioButton radioButton = (RadioButton) rg.findViewById(selectedid);
                                    String str = radioButton.getText().toString();
                                    if (str.equals("Assignment")) {
                                        Intent intent = new Intent(getApplicationContext(), staffassignment.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), staffstudentad.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Select Option", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });
    }
}
