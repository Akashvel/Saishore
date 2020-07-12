package com.example.projecttwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class staffassignment extends AppCompatActivity {

    Button post,del;
    Spinner year,dept,sec;
    EditText e1,e2,e3,e4,e5,e6,e7;
    DatabaseReference databasepublish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffassignment);
        post=(Button)findViewById(R.id.publishbutton);
        year=(Spinner)findViewById(R.id.year);
        dept=(Spinner)findViewById(R.id.dept);
        sec=(Spinner)findViewById(R.id.sec);
        e1=(EditText)findViewById(R.id.subcode);
        e2=(EditText)findViewById(R.id.subname);
        e3=(EditText)findViewById(R.id.tt);
        e4=(EditText)findViewById(R.id.description);
        e5=(EditText)findViewById(R.id.lastdate);
        e6=(EditText)findViewById(R.id.link);
        e7=(EditText)findViewById(R.id.uniqueid);
        del=(Button)findViewById(R.id.deletepost);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adpublish();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delpost();
            }
        });
    }
    public void adpublish(){
        String gotyear = year.getSelectedItem().toString().trim();
        String gotdept = dept.getSelectedItem().toString().trim();
        String gotsec = sec.getSelectedItem().toString().trim();
        String path=gotyear+gotdept+gotsec;
        databasepublish = FirebaseDatabase.getInstance().getReference(path);
        String subcode = e1.getText().toString().trim();
        String subname = e2.getText().toString().trim();
        String title = e3.getText().toString().trim();
        String description = e4.getText().toString().trim();
        String lastdate = e5.getText().toString().trim();
        String link = e6.getText().toString().trim();
        String id = databasepublish.push().getKey();

        publish pb = new publish(id,subcode,subname,title,description,lastdate,link);

        databasepublish.child(id).setValue(pb);
        Toast.makeText(this,"PUBLISHED",Toast.LENGTH_LONG).show();
    }
    private void delpost(){
        String gotyear = year.getSelectedItem().toString().trim();
        String gotdept = dept.getSelectedItem().toString().trim();
        String gotsec = sec.getSelectedItem().toString().trim();
        String path=gotyear+gotdept+gotsec;
        String delid=e7.getText().toString().trim();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query ttle = ref.child(path).orderByChild("title").equalTo(delid);
        ttle.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ttlesnapshot:dataSnapshot.getChildren()){
                    ttlesnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(this,"DELETED SUCCESSFULLY",Toast.LENGTH_LONG).show();
    }
    public Context getContext() {
        return this;
    }
}
