package com.example.itsmeemohit.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.*;
public class BmiData extends AppCompatActivity {

    TextView tvWelcome;
    SharedPreferences sp1;
    Spinner spnFeet,spnInch;
    EditText etWeight;
    Button btnCalculate,btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_data);

        tvWelcome= (TextView) findViewById(R.id.tvWelcome);
        sp1=getSharedPreferences("UserData",MODE_PRIVATE);

        final String name=sp1.getString("name","");
        tvWelcome.setText("Welcome "+name);

        spnFeet= (Spinner) findViewById(R.id.spnFeet);
        spnInch= (Spinner) findViewById(R.id.spnInch);
        etWeight= (EditText) findViewById(R.id.etWeight);
        btnCalculate= (Button) findViewById(R.id.btnCalculate);
        btnHistory= (Button) findViewById(R.id.btnHistory);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ViewHistory.class);
                startActivity(i);
            }
        });

        String[] feet={"1","2","3","4","5","6","7","8"};
        ArrayAdapter<String> ad1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,feet);
        spnFeet.setAdapter(ad1);

        String[] inch={"0","1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter<String> ad2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,inch);
        spnInch.setAdapter(ad2);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ft= (String) spnFeet.getSelectedItem();
                String in= (String) spnInch.getSelectedItem();
                String wt=etWeight.getText().toString();

                if (wt.length()==0){
                    Toast.makeText(getApplicationContext(),"Invalid Weight",Toast.LENGTH_LONG).show();
                    etWeight.requestFocus();
                    return;
                }

                int foot=Integer.parseInt(ft);
                int inches=Integer.parseInt(in);
                double weight=Double.parseDouble(wt);

                while(foot>0){
                    inches=inches+12;
                    foot--;
                }

                double height=inches*2.54;

                double bmi=weight/(height*height);

                bmi=bmi*10000;
                String msg;
                if (bmi<18.5){
                    msg="you are underweight";
                }else if (bmi>=18.5&bmi<25){
                    msg="you are normal";
                }else if (bmi>=25&bmi<30){
                    msg="you are Overweight";
                }else{
                    msg="you are Obese";
                }
                NumberFormat nf=NumberFormat.getInstance();
                nf.setMaximumFractionDigits(2);
                String bmis=nf.format(bmi);

                Intent i=new Intent(BmiData.this,BmiResult.class);
                i.putExtra("bmi",bmis);
                i.putExtra("msg",msg);
                i.putExtra("wt",String.valueOf(weight));
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert=builder.create();
        alert.setTitle("Exit");
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.about:
                Toast.makeText(getApplicationContext(),"Developed By Mohit Ochani",Toast.LENGTH_LONG).show();
                break;
            case R.id.website:
                String url="http://www.mohitochani.com";
                Intent a=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(a);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

























