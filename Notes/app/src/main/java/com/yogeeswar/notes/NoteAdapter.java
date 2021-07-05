package com.yogeeswar.notes;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private String[] name;
    private String[] note;
    private Listener listener;

    interface Listener {
        void onclick(int position);
    }

    public NoteAdapter(String[] name, String[] note) {
        this.name = name;
        this.note = note;
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardview;
        public ViewHolder(CardView v) {
            super(v);
            cardview = v;
        }
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewtype) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cv = holder.cardview;

        TextView name_text = (TextView)cv.findViewById(R.id.card_name_id);
        name_text.setText(name[position]);
        TextView note_text = (TextView)cv.findViewById(R.id.card_note_id);
        note_text.setText(note[position]);
        LinearLayout layout = (LinearLayout)cv.findViewById(R.id.card_layout);

        Random rand = new Random();
        float r = rand.nextFloat()/ 2F + 0.5F;
        float g = rand.nextFloat()/ 2F + 0.5F;
        float b = rand.nextFloat()/ 2F + 0.5F;

        layout.setBackgroundColor(Color.rgb(r,g,b));

        cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener != null)
                    listener.onclick(position);
                return true;
            }
        });
    }
}