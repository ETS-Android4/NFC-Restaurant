package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        user = new User(OrderActivity.this);
        user.checkSession();

        try {
            command = getIntent().getExtras().getParcelableArrayList("command");
        }
        catch(Exception e) {
            e.printStackTrace();
            finish();
        }

        Button validate = (Button) findViewById(R.id.btnValidate);
        TextView back = (TextView) findViewById(R.id.lblBack);
        ListView menu = (ListView) findViewById(R.id.listFood);
        ArrayAdapter<Food> adapter = new ArrayAdapter<>(OrderActivity.this,
                android.R.layout.simple_list_item_1, command);

        menu.setAdapter(adapter);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderActivity.this, NfcActivity.class);
                start(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderActivity.this, MenuActivity.class);
                start(i);
            }
        });
    }

    void start(Intent i) {
        i.putExtra("command", command);
        startActivity(i);
        finish();
    }
}
