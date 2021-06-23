package com.yogeeswar.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setuplist();
    }

    private void setuplist() {
        ListView list = (ListView) findViewById(R.id.list);
        SQLiteOpenHelper tododbhelper = new TodoDbHelper(this);
        try {
            db = tododbhelper.getReadableDatabase();
            cursor = db.query("TASK_LIST", new String[]{"_id","NAME"},
                    null, null, null, null, null);
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_1, cursor,new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            list.setAdapter(adapter);

        }catch (SQLException e) {
            Toast toast = Toast.makeText(this, "database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onClickcreate(View view) {
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Cursor newcursor = db.query("TASK_LIST", new String[]{"_id","NAME"},
                null, null, null, null, null);
        ListView list = (ListView) findViewById(R.id.list);
        CursorAdapter adapter = (CursorAdapter) list.getAdapter();
        adapter.changeCursor(newcursor);
        cursor = newcursor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}