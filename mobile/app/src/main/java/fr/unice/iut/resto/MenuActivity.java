package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "Menu Activity";
    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        try {
            command = getIntent().getExtras().getParcelableArrayList("command");
            user = getIntent().getExtras().getParcelable("user");
        }
        catch(Exception e) {
            Log.e(TAG, e.toString());
            System.exit(0);
        }

        Button entry = (Button) findViewById(R.id.btnEntry);
        Button dish = (Button) findViewById(R.id.btnDish);
        Button dessert = (Button) findViewById(R.id.btnDessert);
        Button drink = (Button) findViewById(R.id.btnDrink);
        Button validate = (Button) findViewById(R.id.btnValidate);

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target", "entree");
                start(i);
            }
        });

        dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target", "plat");
                start(i);
            }
        });

        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target", "dessert");
                start(i);
            }
        });

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target", "boisson");
                start(i);
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (command.size()!=0) {
                    Intent i = new Intent(MenuActivity.this, OrderActivity.class);
                    start(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.errSelect), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void start(Intent i) {
        i.putExtra("command", command);
        i.putExtra("user", user);
        startActivity(i);
        finish();
    }
}
