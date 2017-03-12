package fr.unice.iut.resto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String URL_POST = "";

    RequestVolley queue = RequestVolley.getInstance();
    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connect = (Button) findViewById(R.id.btnConnection);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = (EditText) findViewById(R.id.txtLogin);
                password = (EditText) findViewById(R.id.txtPassword);
                if (login.getText().toString().isEmpty()) {
                    login.setError(getResources().getString(R.string.errLogin));
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.errPassword));
                    return;
                }
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
    }

    public void postRequest() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL_POST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<>();
                params.put("id", login.getText().toString());
                params.put("pw", password.getText().toString());
                return params;
            }
        };
        queue.add(req);
    }
}
