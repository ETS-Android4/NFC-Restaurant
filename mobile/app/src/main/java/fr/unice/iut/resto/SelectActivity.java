package fr.unice.iut.resto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Classe pour afficher les plats disponibles
 * @author ER
 * @version 1.0
 */
public class SelectActivity extends AppCompatActivity {

    private static final String TAG = "Select Activity";
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
        load.setCancelable(false);
        load.setMessage("Loading...");
        load.show();

        adapter = new FoodAdapter(this, list);
        menu = (ListView) findViewById(R.id.listFood);
        Button validate = (Button) findViewById(R.id.btnValidate);
        TextView back = (TextView) findViewById(R.id.lblBack);

        menu.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                int color = ((ColorDrawable) v.getBackground()).getColor();
                int colorSelect = getResources().getColor(R.color.colorAccent);
                if (color != colorSelect) v.setBackgroundColor(colorSelect);
                else v.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        get(target);

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

    /**
     * Envoyer une requête HTTP au serveur pour afficher le menu du type demandé
     * @param tg Type du menu demandé (Entrée, Plat, Dessert, Boisson)
     */
    void get(final String tg) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Requests get = retrofit.create(Requests.class);
        Call<JsonArray> call = get.getMenu(tg);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                load.dismiss();
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
                                    tg
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
                    Toast.makeText(getApplicationContext(), "Error " + response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                load.dismiss();
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.errConnection), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Mettre en surbrillance les choix précédents d'un utilisateur
     */
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

    /**
     * Enregistrer les choix d'un utilisateur
     */
    void start() {
        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
        i.putExtra("command", command);
        startActivity(i);
        finish();
    }
}
