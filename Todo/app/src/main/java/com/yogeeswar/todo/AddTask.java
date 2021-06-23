package com.yogeeswar.todo;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }
    public void onClickadd(View view) {
        TextView task = (TextView) findViewById(R.id.addtask);
        String data = task.getText().toString();

        SQLiteOpenHelper tododbhelper = new TodoDbHelper(this);
        try {
            SQLiteDatabase db = tododbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("NAME",data);
            if( db.insert("TASK_LIST",null, values) == -1) {
                Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                task.setText("");
            }

        }catch (SQLException e) {
            Toast.makeText(this, "database unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}