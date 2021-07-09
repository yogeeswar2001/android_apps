package com.yogeeswar.movieapp.util;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.yogeeswar.movieapp.models.MovieData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDataService {
    private static final String API_KEY = "52e2dd84808d1a40717149f8b0f39d4c";
    private static final String TOP_RATED_URL = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1&api_key="+API_KEY;
    private static final String UPCOMING_URL = "https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1&api_key="+API_KEY;
    private static final String SEARCH_URL = "https://api.themoviedb.org/3/search/movie?language=en-US&page=1&api_key="+API_KEY+"&query=";

    private Context context;

    public interface MovieResultListener {
        void onResponse(ArrayList<MovieData> movieData);
        void onError(String msg);
    }
    public void getTopRatedMovies(MovieResultListener listener) {
        RequestHandler(listener, TOP_RATED_URL);
    }
    public void getUpcomingMovies(MovieResultListener listener) {
        RequestHandler(listener, UPCOMING_URL);
    }

    public void getMovieByName(String movieName, MovieResultListener listener) {
        String url = SEARCH_URL+movieName;
        RequestHandler(listener, url);
    }

    private void RequestHandler(final MovieResultListener listener, String url) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<MovieData> dataList = new ArrayList<MovieData>();
                        MovieData data;
                        JSONObject temp;
                        try {
                            JSONArray list = response.getJSONArray("results");
                            int n = list.length();
                            for(int i=0;i<n;i++) {
                                temp = list.getJSONObject(i);
                                data = new MovieData();
                                data.setTitle(temp.getString("title"));
                                data.setPosterPath(temp.getString("poster_path"));
                                data.setBackdropPath(temp.getString("backdrop_path"));
                                data.setReleaseDate(temp.getString("release_date"));
                                data.setRating(temp.getString("vote_average"));
                                data.setOverview(temp.getString("overview"));
                                
                                dataList.add(data);
                            }
                            listener.onResponse(dataList);
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.toString());
            }
        });

        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    public MovieDataService(Context context){
        this.context = context;
    }
}
