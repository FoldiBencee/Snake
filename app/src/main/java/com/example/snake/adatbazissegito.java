package com.example.snake;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class adatbazissegito extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME ="snake";
    public static  final String TABLE_NAME ="registrationee";


    public  static  final String COL_1 = "ID";
    public  static  final String COL_2 = "felhasznalonev";
    public  static  final String COL_3 = "jelszo";
    public  static  final String COL_4 = "jelszoismet";
    public  static  final String COL_5 = "email";
    //public  static  final String COL_5 = "teljesnev";




    public adatbazissegito(Context context)
    {
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, felhasznalonev TEXT not null,jelszo TEXT not null,jelszoismet TEXT not null,email TEXT not null)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean adatRogzites(String felhasznalonev, String jelszo, String jelszoismet, String email)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,felhasznalonev);
        contentValues.put(COL_3,jelszo);
        contentValues.put(COL_4,jelszoismet);
        contentValues.put(COL_5,email);




        Long eredmeny = database.insert(TABLE_NAME, null,contentValues);
        if (eredmeny == -1)
            return false;
        else
            return  true;

    }

    public Cursor adatLekerdezes()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor eredmeny = database.rawQuery("SELECT * from " + TABLE_NAME, null);
        return eredmeny;

    }






}
