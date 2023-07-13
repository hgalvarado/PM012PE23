package com.hgalvarado.exercise23.Configuration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteConexion extends SQLiteOpenHelper {

    //Variables
    private static final String nameDataBase = "PM12PE23";
    private static final int versionDataBase = 1;

    private static final String tableName = "photos";
    private static final String columnid = "Id";
    private static final String columnphoto = "photo";
    private static final String columndescription = "description";

    public SQLiteConexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDataBase, null,versionDataBase );
    }
    private static final String CreateTable =
            "CREATE TABLE " + tableName + "(" +
                    columnid + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    columnphoto + " BLOB," +
                    columndescription + " TEXT" +
                    ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion de objetos de bases de datos
        db.execSQL(CreateTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }
    public void InsertPhoto(byte[] photo, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnphoto, photo);
        values.put(columndescription, description);
        db.insert(tableName, null, values);
        db.close();
    }
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
    }
}
