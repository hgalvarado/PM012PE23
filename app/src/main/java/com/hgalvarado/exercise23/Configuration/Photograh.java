package com.hgalvarado.exercise23.Configuration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Photograh {
    //Variables
    public static final String nameDataBase = "PM12PE23";
    public static final int versionDataBase = 1;
    public static final String tableName = "photos";
    public static final String columnid = "Id";
    public static final String columnphoto = "photo";
    public static final String columndescription = "description";


    public static final String CreateTable =
            "CREATE TABLE " + tableName + "(" +
                    columnid + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    columnphoto + " BLOB," +
                    columndescription + " TEXT" +
                    ")";
    public static final String DropTablePhotos = "DROP TABLE IF EXISTS " + tableName;

/*
    public List<Photograh> getAllFotos() {
        List<Photograh> fotosList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Photograh fotos = new Photograh();
                fotos.setId(Integer.parseInt(cursor.getString(0)));
                fotos.setPhoto(cursor.getBlob(1));
                fotos.setDescription(cursor.getString(2));
                fotosList.add(fotos);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return fotosList;
    }*/

}
