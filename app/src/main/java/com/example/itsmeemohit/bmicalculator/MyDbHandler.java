package com.example.itsmeemohit.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by itsmeemohit on 15-01-2017.
 */

public class MyDbHandler extends SQLiteOpenHelper{

    Context context;
    SQLiteDatabase db;

    MyDbHandler(Context context){
        super(context,"bmidatabase",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table bmitable(dt text,bmi text,wt text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addRecord(String dt,String bmi,String wt){
        ContentValues cv=new ContentValues();
        cv.put("dt",dt);
        cv.put("bmi",bmi);
        cv.put("wt",wt);
        db.insert("bmitable",null,cv);
    }
    public String getRecord(){
        StringBuffer sb=new StringBuffer();
        Cursor cs=db.query("bmitable",null,null,null,null,null,null);
        if (cs.getCount()>0){
            cs.moveToFirst();
            do{
                String d=cs.getString(0);
                String b=cs.getString(1);
                String w=cs.getString(2);
                sb.append(d + " "+ b +" "+w+"\n");

            }while(cs.moveToNext());
        }

        return sb.toString();
    }

}
