package fr.unice.iut.resto;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestVolley extends Application {

    private static RequestVolley instance;
    private RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static synchronized RequestVolley getInstance() {
        return instance;
    }

    public RequestQueue newRequestQueue() {
        return queue;
    }

    public <T> void add(Request<T> req) {
        newRequestQueue().add(req);
    }
}
