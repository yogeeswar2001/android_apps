package com.yogeeswar.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.yogeeswar.movieapp.adapter.MovieAdapter;
import com.yogeeswar.movieapp.models.MovieData;
import com.yogeeswar.movieapp.util.MovieDataService;

import java.util.ArrayList;
import java.util.Objects;

public class UpcomingMovieFragment extends Fragment {
    public UpcomingMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView upcomingMovieRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_top_movie, container, false);

        MovieDataService dataService = new MovieDataService(getContext());
        final MovieAdapter adapter = new MovieAdapter(MainActivity.upcomingMovieData);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        upcomingMovieRecycler.setAdapter(adapter);
        upcomingMovieRecycler.setLayoutManager(layoutManager);

        dataService.getUpcomingMovies(new MovieDataService.MovieResultListener() {
            @Override
            public void onResponse(ArrayList<MovieData> movieData) {
                for(MovieData i : movieData) {
                    MainActivity.upcomingMovieData.add(i);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setListener(new MovieAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_POSITION_ID, position);
                intent.putExtra(MovieDetailActivity.FRAGMENT_ID, 1);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
        return upcomingMovieRecycler;
    }
}