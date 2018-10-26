package com.example.itsmeemohit.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName,etAge,etPhoneNumber;
    RadioGroup rgSex;
    Button btnRegister;
    SharedPreferences sp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= (EditText) findViewById(R.id.etName);
        etAge= (EditText) findViewById(R.id.etAge);
        etPhoneNumber= (EditText) findViewById(R.id.etPhoneNumber);
        rgSex= (RadioGroup) findViewById(R.id.rgSex);
        btnRegister= (Button) findViewById(R.id.btnRegister);
        sp1=getSharedPreferences("UserData",MODE_PRIVATE);

        if (sp1.getBoolean("ne",false)==false){
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name=etName.getText().toString();
                    String age=etAge.getText().toString();
                    String phoneNumber=etPhoneNumber.getText().toString();

                    if (name.length()==0){
                        Toast.makeText(getApplicationContext(),"Invalid Name",Toast.LENGTH_LONG).show();
                        etName.requestFocus();
                        return;
                    }
                    if (age.length()==0){
                        Toast.makeText(getApplicationContext(),"Invalid Age",Toast.LENGTH_LONG).show();
                        etAge.requestFocus();
                        return;
                    }
                    if (phoneNumber.length()!=10){
                        Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_LONG).show();
                        etPhoneNumber.requestFocus();
                        return;
                    }
                    int s=rgSex.getCheckedRadioButtonId();
                    RadioButton rb= (RadioButton) findViewById(s);
                    String sex=rb.getText().toString();

                    SharedPreferences.Editor editor=sp1.edit();
                    editor.putString("name",name);
                    editor.putString("age",age);
                    editor.putString("phonenumber",phoneNumber);
                    editor.putString("sex",sex);
                    editor.putBoolean("ne",true);
                    editor.commit();

                    Intent i=new Intent(getApplicationContext(),BmiData.class);
                    startActivity(i);
                    finish();

                }
            });
        }
        else{
            Intent i=new Intent(getApplicationContext(),BmiData.class);
            startActivity(i);
            finish();
        }

    }
}
