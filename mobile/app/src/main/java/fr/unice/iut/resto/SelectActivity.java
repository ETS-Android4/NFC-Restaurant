package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectActivity extends AppCompatActivity {

    private static final String TAG = "SelectActivity";
    private static final String URL_GET = "";

    RequestVolley queue = RequestVolley.getInstance();
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> choice = new ArrayList<>();
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ListView dish = (ListView) findViewById(R.id.listDish);
        Button back = (Button) findViewById(R.id.btnBack);

        target = getIntent().getExtras().getString("target");
        getRequest();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SelectActivity.this,
                android.R.layout.simple_list_item_multiple_choice, list);
        dish.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dish.setAdapter(adapter);
        dish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                if (choice.contains(list.get(position)))
                    choice.remove(list.get(position));
                else
                    choice.add(list.get(position));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectActivity.this, MenuActivity.class);
                i.putExtra("choice",choice.toString());
                startActivity(i);
                finish();
            }
        });
    }

    public void getRequest() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL_GET+target, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for (Iterator it = response.keys(); it.hasNext();)
                                list.add((String) response.get(String.valueOf(it.next())));
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                }
        );
        queue.add(req);
    }
}
