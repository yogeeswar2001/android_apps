package com.yogeeswar.movieapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String FRAGMENT_ID = "fragmentId";
    public static final String MOVIE_POSITION_ID = "movieId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //setting toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int fragmentId = (Integer)getIntent().getExtras().get(FRAGMENT_ID);
        int movieId = (Integer)getIntent().getExtras().get(MOVIE_POSITION_ID);

        System.out.println(fragmentId+"+++++++++++++++++"+movieId);

        String titleStr,overviewStr,backdropPath;
        switch(fragmentId) {
            case 0:
                titleStr = MainActivity.topMovieData.get(movieId).getTitle();
                overviewStr = MainActivity.topMovieData.get(movieId).getOverview();
                backdropPath = MainActivity.topMovieData.get(movieId).getBackdropPath();
                break;
            case 1:
                titleStr = MainActivity.upcomingMovieData.get(movieId).getTitle();
                overviewStr = MainActivity.upcomingMovieData.get(movieId).getOverview();
                backdropPath = MainActivity.upcomingMovieData.get(movieId).getBackdropPath();
                break;
            default:
                titleStr = MainActivity.searchMovieData.get(movieId).getTitle();
                overviewStr = MainActivity.searchMovieData.get(movieId).getOverview();
                backdropPath = MainActivity.searchMovieData.get(movieId).getBackdropPath();
        }

        TextView title = (TextView) findViewById(R.id.title);
        TextView overview = (TextView) findViewById(R.id.overview);
        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);

        title.setText(titleStr);
        overview.setText(overviewStr);

        String img_url = "https://image.tmdb.org/t/p/original"+backdropPath;
        Picasso.get().load(img_url).fit().into(backdrop);
    }
}