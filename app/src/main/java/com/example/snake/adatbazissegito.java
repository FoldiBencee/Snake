package com.example.snake;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class adatbazissegito extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME ="SanekeRegisztracio";
    public static  final String TABLE_NAME ="regisztracio";


    public  static  final String COL_1 = "ID";
    public  static  final String COL_2 = "felhasznalonev";
    public  static  final String COL_3 = "jelszo";

    public adatbazissegito(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, felhasznalonev TEXT not null,jelszo TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public  boolean adatRogzites(String felhasznalonev, String jelszo)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,felhasznalonev);
        contentValues.put(COL_3,jelszo);

        long eredmeny = database.insert(TABLE_NAME, null,contentValues);
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

    public long adatTorles(int id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME,COL_1+" = ?",new String[] {String.valueOf(id)});

    }



}
