package com.yogeeswar.notes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addnote_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.save_note:
                EditText note_name = (EditText) findViewById(R.id.note_name);
                EditText note_text = (EditText) findViewById(R.id.note_text);
                String name = note_name.getText().toString();
                String note = note_text.getText().toString();

                if(name.equals("") || note.equals("")) {
                    Toast toast = Toast.makeText(this, "enter details", Toast.LENGTH_SHORT);
                    return true;
                }
                save_note(name, note);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save_note(String name, String note) {
        SQLiteOpenHelper dbhelper = new NotesDbHelper(this);
        Toast toast;
        try {
            db = dbhelper.getWritableDatabase();
            Cursor cursor = db.query("notes", new String[] {"NAME"}, "NAME=?", new String[] {name}, null, null, null);

            ContentValues data = new ContentValues();
            data.put("NOTE", note);

            if(cursor.moveToFirst()) {
                db.update("notes", data, "name=?", new String[] {name});
                toast = Toast.makeText(this, "updated", Toast.LENGTH_SHORT);
                toast.show();
                cursor.close();
                return;
            }

            data.put("NAME", name);
            db.insert("notes", null, data);

            toast = Toast.makeText(this, "saved", Toast.LENGTH_SHORT);
            toast.show();
            cursor.close();
        }catch(SQLiteException e) {
            toast = Toast.makeText(this, "database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}