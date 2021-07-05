package com.yogeeswar.notes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class NoteListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SQLiteOpenHelper dbHelper =  new NotesDbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("notes", new String[]{"_id","NAME","NOTE"}, null, null, null, null, null);
        int i=0;
        final String[] name = new String[cursor.getCount()];
        final String[] note = new String[cursor.getCount()];
        if(cursor.moveToFirst()) {
            do {
                name[i]=cursor.getString(1);
                note[i]=cursor.getString(2);
                i++;
            } while(cursor.moveToNext());
        }
        RecyclerView noteRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_note, container, false);

        final NoteAdapter adapter = new NoteAdapter(name, note);
        noteRecycler.setAdapter(adapter);
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        noteRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new NoteAdapter.Listener() {
            @Override
            public void onclick(int position) {
                SQLiteOpenHelper dbHelper =  new NotesDbHelper(getActivity());
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                db.delete("notes","NAME=?", new String[] {name[position]});
                //adapter.notifyItemRemoved(position);

                Toast toast = Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        cursor.close();
        db.close();
        return noteRecycler;
    }
}