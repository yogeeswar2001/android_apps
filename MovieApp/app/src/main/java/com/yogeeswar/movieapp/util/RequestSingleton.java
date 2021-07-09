package com.yogeeswar.movieapp.util;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestSingleton {
    private static RequestSingleton instance;
    private RequestQueue queue;
    private static Context context;

    private RequestSingleton(Context ctx) {
        context = ctx;
        queue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(queue == null )
            queue = Volley.newRequestQueue(context.getApplicationContext());
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
    public static synchronized RequestSingleton getInstance(Context ctx) {
        if(instance == null )
            instance = new RequestSingleton(ctx);
        return instance;
    }
}
