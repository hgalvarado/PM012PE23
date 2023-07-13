package com.hgalvarado.exercise23.Configuration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Photograh {
    private int id;
    private byte[] photo;
    private String description;
    public Photograh() {
    }
    public Photograh(int id, byte[] photo, String description) {
        this.id = id;
        this.photo = photo;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
