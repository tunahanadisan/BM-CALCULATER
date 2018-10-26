package com.example.itsmeemohit.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;

public class BmiResult extends AppCompatActivity {

    TextView tvResult,tvUn,tvNo,tvOv,tvOb;
    Button btnBack,btnShare,btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        tvResult= (TextView) findViewById(R.id.tvResult);
        tvUn= (TextView) findViewById(R.id.tvUn);
        tvNo= (TextView) findViewById(R.id.tvNo);
        tvOv= (TextView) findViewById(R.id.tvOv);
        tvOb= (TextView) findViewById(R.id.tvOb);
        btnBack= (Button) findViewById(R.id.btnBack);
        btnShare= (Button) findViewById(R.id.btnShare);
        btnSave= (Button) findViewById(R.id.btnSave);

        final Intent i=getIntent();
        final String msg=i.getStringExtra("msg");
        String bmi=i.getStringExtra("bmi");
        String txt="Your BMI is "+bmi+" and "+msg;
        tvResult.setText(txt);

        final double bmid=Double.parseDouble(bmi);
        if (bmid<18.5){
            tvUn.setTextColor(Color.parseColor("#ff0000"));
        }
        else if (bmid>=18.5&bmid<25){
            tvNo.setTextColor(Color.GREEN);
        }
        else if (bmid>=25&bmid<30){
            tvOv.setTextColor(Color.YELLOW);
        }
        else{
            tvOb.setTextColor(Color.parseColor("#ff0000"));
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp1=getSharedPreferences("UserData",MODE_PRIVATE);
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String name=sp1.getString("name","");
                String age=sp1.getString("age","");
                String phone=sp1.getString("phonenumber","");
                String sex=sp1.getString("sex","");
                String details="Name : "+name+"\n"
                        +"Age :"+age+"\n"
                        +"Phone :"+phone+"\n"
                        +"BMI :"+bmid+"\n"
                        +msg+"\n";
                i.putExtra(Intent.EXTRA_TEXT,details);
                startActivity(i);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w=i.getStringExtra("wt");
                String b=i.getStringExtra("bmi");
                Date d=new Date();
                MyDbHandler db=new MyDbHandler(getApplicationContext());
                db.addRecord(d.toString(),b,w);
            }
        });

    }

}
