package com.yogeeswar.todo;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class TodoDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "TODO_DB";
    private static final int DB_VERSION = 1;

    TodoDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        update_db(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        update_db(db, oldversion, newversion);
    }

    private void update_db(SQLiteDatabase db, int oldversion, int newversion) {
        if(oldversion < 1) {
            //String sql = "CREATE TABLE TASK_LIST (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT);";
            db.execSQL("CREATE TABLE TASK_LIST ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "NAME TEXT);");
        }
    }
}
