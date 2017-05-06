package fr.unice.iut.resto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    ArrayList<Food> list = new ArrayList<>();
    ProgressDialog load;
    FoodAdapter adapter;
    ListView menu;
    String target;
    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        user = new User(this);
        user.checkSession();

        target = getIntent().getExtras().getString("target");
        command = getIntent().getExtras().getParcelableArrayList("command");

        load = new ProgressDialog(this);
        load.setIndeterminate(true);
        load.setMessage("Loading...");
        load.show();

        Button validate = (Button) findViewById(R.id.btnValidate);
        TextView back = (TextView) findViewById(R.id.lblBack);
        menu = (ListView) findViewById(R.id.listFood);
        adapter = new FoodAdapter(this, list);

        menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int color = ((ColorDrawable) view.getBackground()).getColor();
                int colorSelect = getResources().getColor(R.color.colorAccent);
                if (color != colorSelect)
                    view.setBackgroundColor(colorSelect);
                else
                    view.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        get();

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = command.size()-1;
                for (int i=size; i>=0; i--) {
                    if (command.get(i).getType().equals(target)) {
                        command.remove(i);
                    }
                }
                SparseBooleanArray checked = menu.getCheckedItemPositions();
                for (int i=0; i<menu.getAdapter().getCount(); i++) {
                    if (checked.get(i)) {
                        command.add((Food) menu.getAdapter().getItem(i));
                    }
                }
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Requests get = retrofit.create(Requests.class);
        Call<JsonArray> call = get.getMenu(target);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (load.isShowing()) load.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONArray json = new JSONArray(response.body().toString());
                        for (int i=0; i<json.length(); i++) {
                            Food food = new Food(
                                    json.getJSONObject(i).getInt("Id"),
                                    json.getJSONObject(i).getString("Nom"),
                                    json.getJSONObject(i).getString("Description"),
                                    json.getJSONObject(i).getDouble("Prix"),
                                    json.getJSONObject(i).getString("Url"),
                                    target
                            );
                            list.add(food);
                        }
                        menu.setAdapter(adapter);
                        record();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                if (load.isShowing()) load.dismiss();
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    void record() {
        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<command.size(); j++) {
                if (command.get(j).getCode() == list.get(i).getCode()) {
                    menu.performItemClick(menu.getAdapter().getView(i, null, null), i,
                            menu.getAdapter().getItemId(i));
                }
            }
        }
    }

    void start() {
        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
        i.putExtra("command", command);
        startActivity(i);
        finish();
    }
}
