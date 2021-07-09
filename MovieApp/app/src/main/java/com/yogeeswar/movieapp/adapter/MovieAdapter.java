package com.yogeeswar.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.yogeeswar.movieapp.R;
import com.yogeeswar.movieapp.models.MovieData;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final ArrayList<MovieData> data;
    public Listener listener;
    public interface Listener {
        void onClick(int position);
    }

    public MovieAdapter(ArrayList<MovieData> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        public ViewHolder(CardView v) {
            super(v);
            cv=v;
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardview = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_moviedata, parent, false);
        return new ViewHolder(cardview);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CardView card = holder.cv;

        TextView movieName = (TextView) card.findViewById(R.id.movieName);
        TextView date = (TextView) card.findViewById(R.id.releaseDate);
        TextView rating = (TextView) card.findViewById(R.id.rating);
        ImageView poster = (ImageView) card.findViewById(R.id.poster);

        movieName.setText(data.get(position).getTitle());
        date.setText(data.get(position).getReleaseDate());
        rating.setText(data.get(position).getRating());

        if(data.get(position).getPosterPath() != null ) {
            String poster_url = "https://image.tmdb.org/t/p/original" + data.get(position).getPosterPath();
            Picasso.get().load(poster_url).fit().into(poster);
        }
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                    listener.onClick(holder.getAdapterPosition());
            }
        });
    }
}
