package com.example.itsmeemohit.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewHistory extends AppCompatActivity {

    TextView tvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        tvHistory= (TextView) findViewById(R.id.tvHistory);
        MyDbHandler db=new MyDbHandler(getApplicationContext());
        String m=db.getRecord();
        tvHistory.setText(m);
    }
}
