package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "OrderActivity";
    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        try {
            command = getIntent().getExtras().getParcelableArrayList("command");
            user = getIntent().getExtras().getParcelable("user");
        }
        catch(Exception e) {
            Log.e(TAG, e.toString());
            System.exit(0);
        }

        ListView menu = (ListView) findViewById(R.id.listFood);
        Button validate = (Button) findViewById(R.id.btnValidate);
        TextView back = (TextView) findViewById(R.id.lblBack);
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
        i.putExtra("user", user);
        startActivity(i);
        finish();
    }
}
