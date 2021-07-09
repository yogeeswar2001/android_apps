package com.yogeeswar.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.yogeeswar.movieapp.adapter.MovieAdapter;
import com.yogeeswar.movieapp.models.MovieData;
import com.yogeeswar.movieapp.util.MovieDataService;

import java.util.ArrayList;
import java.util.Objects;

public class SearchMovieFragment extends Fragment {
    MovieAdapter adapter = new MovieAdapter(MainActivity.searchMovieData);
    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final MovieDataService dataService = new MovieDataService(getContext());
        // Inflate the layout for this fragment
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_search_movie, container, false);
        RecyclerView searchRecycler = layout.findViewById(R.id.searchMovieRecycler);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        searchRecycler.setAdapter(adapter);
        searchRecycler.setLayoutManager(layoutManager);

        ImageButton search_btn = layout.findViewById(R.id.search_btn);
        final EditText search_bar = layout.findViewById(R.id.searchBar);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataService.getMovieByName(search_bar.getText().toString(), new MovieDataService.MovieResultListener() {
                    @Override
                    public void onResponse(ArrayList<MovieData> movieData) {
                        MainActivity.searchMovieData.clear();
                        for(MovieData i : movieData)
                            MainActivity.searchMovieData.add(i);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        adapter.setListener(new MovieAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_POSITION_ID, position);
                intent.putExtra(MovieDetailActivity.FRAGMENT_ID, 2);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });

        return layout;
    }
}