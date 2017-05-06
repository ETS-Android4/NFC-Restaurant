package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        user = new User(this);
        user.checkSession();

        try {
            command = getIntent().getExtras().getParcelableArrayList("command");
        }
        catch (Exception e) {
            command = new ArrayList<>();
        }

        Button entry = (Button) findViewById(R.id.btnEntry);
        Button dish = (Button) findViewById(R.id.btnDish);
        Button dessert = (Button) findViewById(R.id.btnDessert);
        Button drink = (Button) findViewById(R.id.btnDrink);
        Button validate = (Button) findViewById(R.id.btnValidate);
        TextView exit = (TextView) findViewById(R.id.lblExit);

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start("entree");
            }
        });
        dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start("plat");
            }
        });
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start("dessert");
            }
        });
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start("boisson");
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (command.size() != 0) {
                    Intent i = new Intent(getApplicationContext(), OrderActivity.class);
                    i.putExtra("command", command);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.errSelect), Toast.LENGTH_LONG).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.exitSession();
            }
        });
    }

    void start(String type) {
        Intent i = new Intent(getApplicationContext(), SelectActivity.class);
        i.putExtra("target", type);
        i.putExtra("command", command);
        startActivity(i);
        finish();
    }
}
