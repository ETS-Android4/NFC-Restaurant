package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DishActivity extends AppCompatActivity {

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
                finish();
                startActivity(new Intent(DishActivity.this, MenuActivity.class));
            }
        });
    }
}
