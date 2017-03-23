package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "Menu Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button entry = (Button) findViewById(R.id.btnEntry);
        Button dish = (Button) findViewById(R.id.btnDish);
        Button dessert = (Button) findViewById(R.id.btnDessert);
        Button drink = (Button) findViewById(R.id.btnDrink);
        Button validate = (Button) findViewById(R.id.btnValidate);

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target","entree");
                startActivity(i);
            }
        });

        dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target","plat");
                startActivity(i);
            }
        });

        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target","dessert");
                startActivity(i);
            }
        });

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectActivity.class);
                i.putExtra("target","boisson");
                startActivity(i);
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, OrderActivity.class));
            }
        });
    }
}
