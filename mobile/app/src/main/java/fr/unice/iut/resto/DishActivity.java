package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public class DishActivity extends AppCompatActivity {

    private static final String URL_GET = "";

    RequestVolley queue = RequestVolley.getInstance();
    ArrayList<String> list = new ArrayList<>();
    String[] choice = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        ListView dish = (ListView) findViewById(R.id.listDish);
        Button back = (Button) findViewById(R.id.btnBack);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(DishActivity.this,
                android.R.layout.simple_list_item_multiple_choice, list);
        dish.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DishActivity.this, MenuActivity.class));
            }
        });
    }

    public void getRequest() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL_GET, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        );
        queue.add(req);
    }
}
