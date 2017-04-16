package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectActivity extends AppCompatActivity {

    private static final String TAG = "SelectActivity";
    JSONArray json = new JSONArray();
    ArrayList<Food> list = new ArrayList<>();
    ArrayAdapter<Food> adapter;
    ListView menu;
    String target;
    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        try {
            target = getIntent().getExtras().getString("target");
            command = getIntent().getExtras().getParcelableArrayList("command");
            user = getIntent().getExtras().getParcelable("user");
        }
        catch(Exception e) {
            Log.e(TAG, e.toString());
        }

        Button validate = (Button) findViewById(R.id.btnValidate);
        TextView back = (TextView) findViewById(R.id.lblBack);
        menu = (ListView) findViewById(R.id.listFood);
        adapter = new ArrayAdapter<>(SelectActivity.this, android.R.layout.simple_list_item_multiple_choice, list);

        menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        get();

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = command.size()-1;
                for (int i=size; i>=0; i--)
                    if (command.get(i).getType().equals(target))
                        command.remove(i);
                SparseBooleanArray checked = menu.getCheckedItemPositions();
                for (int i=0; i<menu.getAdapter().getCount(); i++)
                    if (checked.get(i))
                        command.add((Food)menu.getAdapter().getItem(i));
                start();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
    }

    void get() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Requests get = retrofit.create(Requests.class);
        Call<JsonArray> call = get.getMenu(target);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.code()>199 && response.code()<206) {
                    try {
                        json = new JSONArray(response.body().toString());
                        for(int i=0; i<json.length(); i++) {
                            Food food = new Food(
                                    json.getJSONObject(i).getInt("Id"),
                                    json.getJSONObject(i).getString("Nom"),
                                    json.getJSONObject(i).getString("Description"),
                                    json.getJSONObject(i).getDouble("Prix"),
                                    target
                            );
                            list.add(food);
                        }
                        menu.setAdapter(adapter);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.e(TAG, String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    void start() {
        Intent i = new Intent(SelectActivity.this, MenuActivity.class);
        i.putExtra("command", command);
        i.putExtra("user", user);
        startActivity(i);
        finish();
    }
}
