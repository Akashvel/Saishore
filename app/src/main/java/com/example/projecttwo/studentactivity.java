package com.example.projecttwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.app.ProgressDialog;
import android.widget.Toast;

public class studentactivity extends AppCompatActivity {
    TextView t1,ttl;
    Spinner year,dept,sec;
    Button b1;
    DatabaseReference databasepublish;
    int l=0,j,i;
    int l1=0;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentactivity);
        t1=(TextView)findViewById(R.id.resultview);
        ttl=(TextView)findViewById(R.id.ttl);
        Animation anim = new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        ttl.startAnimation(anim);

        year=(Spinner)findViewById(R.id.year);
        dept=(Spinner)findViewById(R.id.dept);
        sec=(Spinner)findViewById(R.id.sec);
        pb=(ProgressBar)findViewById(R.id.pgbar);
        b1=(Button)findViewById(R.id.searchbutton);
        t1.setMovementMethod(new ScrollingMovementMethod());
        databasepublish = FirebaseDatabase.getInstance().getReference("general");
        databasepublish.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[] dess = new String[101];
                String[] aboouut = new String[101];
                for(DataSnapshot postsnapshot: dataSnapshot.getChildren()){
                    post pb = postsnapshot.getValue(post.class);
                    aboouut[l]=pb.getAbout();
                    dess[l]=pb.getDes();
                    l++;
                }
                pb.setVisibility(View.GONE);
                SpannableStringBuilder builder = new SpannableStringBuilder();
                for(j=l-1;j>=0;j--){
                    String s1 = aboouut[j];
                    String s2 = dess[j];

                    SpannableString str1 = new SpannableString("ABOUT: ");
                    str1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str1.length(), 0);
                    builder.append(str1);

                    SpannableString str2 = new SpannableString(s1+"\n");
                    str2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str2.length(), 0);
                    builder.append(str2);

                    SpannableString str3 = new SpannableString("DESCRIPTION: ");
                    str3.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str3.length(), 0);
                    builder.append(str3);

                    SpannableString str4 = new SpannableString(s2+"\n..........................\n");
                    str4.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str4.length(), 0);
                    builder.append(str4);

                }
                t1.setText(builder, TextView.BufferType.SPANNABLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("");
                l=0;
                post();
            }
        });

    }
    public void post(){
        String gotyear = year.getSelectedItem().toString().trim();
        String gotdept = dept.getSelectedItem().toString().trim();
        final String gotsec = sec.getSelectedItem().toString().trim();
        String path=gotyear+gotdept+gotsec;

        databasepublish = FirebaseDatabase.getInstance().getReference(path);
        databasepublish.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[] subcode = new String[10001];
                String[] subname = new String[10001];
                String[] subtitle = new String[10001];
                String[] subdes = new String[10001];
                String[] lastdate = new String[10001];
                String[] id = new String[10001];
                l=0;
                SpannableStringBuilder builder = new SpannableStringBuilder();
                for(DataSnapshot postsnapshot: dataSnapshot.getChildren()){
                    publish pb = postsnapshot.getValue(publish.class);
                    subname[l]=pb.getSubname();
                    subcode[l]=pb.getSubcode();
                    subtitle[l]=pb.getTitle();
                    subdes[l]=pb.getDescription();
                    lastdate[l]=pb.getLastdate();
                    id[l]=pb.getId();
                    l++;
                }
                for(j=l-1;j>=0;j--){
                    //t1.setText(t1.getText()+"\nSUBCODE: "+subcode[j]+"\nSUBNAME: "+subname[j]+"\nTITLE: "+subtitle[j]+"\nDECRIPTION: "+subdes[j]+"\nLASTDATE TO SUBMIT: "+lastdate[j]+"\nID: "+id[j]+"\n...............");
                    if(gotsec.equals("ALL")){
                        SpannableString str5 = new SpannableString("ABOUT: ");
                        str5.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str5.length(), 0);
                        builder.append(str5);

                        SpannableString str6 = new SpannableString(subtitle[j]+"\n");
                        str6.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str6.length(), 0);
                        builder.append(str6);

                        SpannableString str7 = new SpannableString("DESCRIPTION: ");
                        str7.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str7.length(), 0);
                        builder.append(str7);

                        SpannableString str8 = new SpannableString(subdes[j]+"\n");
                        str8.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str8.length(), 0);
                        builder.append(str8);
                    }
                    else {
                        SpannableString str1 = new SpannableString("SUBCODE: ");
                        str1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str1.length(), 0);
                        builder.append(str1);

                        SpannableString str2 = new SpannableString(subcode[j] + "\n");
                        str2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str2.length(), 0);
                        builder.append(str2);

                        SpannableString str3 = new SpannableString("SUBNAME: ");
                        str3.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str3.length(), 0);
                        builder.append(str3);

                        SpannableString str4 = new SpannableString(subname[j] + "\n");
                        str4.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str4.length(), 0);
                        builder.append(str4);

                        SpannableString str5 = new SpannableString("TITLE: ");
                        str5.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str5.length(), 0);
                        builder.append(str5);

                        SpannableString str6 = new SpannableString(subtitle[j] + "\n");
                        str6.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str6.length(), 0);
                        builder.append(str6);

                        SpannableString str7 = new SpannableString("DESCRIPTION: ");
                        str7.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str7.length(), 0);
                        builder.append(str7);

                        SpannableString str8 = new SpannableString(subdes[j] + "\n");
                        str8.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str8.length(), 0);
                        builder.append(str8);

                        SpannableString str9 = new SpannableString("LASTDATE: ");
                        str9.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str9.length(), 0);
                        builder.append(str9);

                        SpannableString str10 = new SpannableString(lastdate[j] + "\n.........................\n");
                        str10.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str10.length(), 0);
                        builder.append(str10);
                    }
                }
                t1.setText(builder, TextView.BufferType.SPANNABLE);
                l=0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
            }
        });
    }
    public Context getContext() {
        return this;
    }
}
