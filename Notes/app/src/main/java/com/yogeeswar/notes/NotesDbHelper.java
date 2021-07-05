package com.yogeeswar.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "notes_app";
    private static final int DB_VERSION = 1;

    NotesDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        setupdb(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        setupdb(db, oldversion, newversion);
    }

    private void setupdb(SQLiteDatabase db, int oldversion, int newversion){
        if(oldversion < 1) {
            String sql = "create table notes(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, NOTE TEXT);";
            db.execSQL(sql);
        }
    }
}