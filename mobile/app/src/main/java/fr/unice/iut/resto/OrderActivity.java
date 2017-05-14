package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Classe pour afficher les choix d'un utilisateur
 * @author ER
 * @version 1.0
 */
public class OrderActivity extends AppCompatActivity {

    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        user = new User(this);
        user.checkSession();

        command = getIntent().getExtras().getParcelableArrayList("command");
        FoodAdapter adapter = new FoodAdapter(this, command);
        ListView menu = (ListView) findViewById(R.id.listFood);
        Button validate = (Button) findViewById(R.id.btnValidate);
        TextView back = (TextView) findViewById(R.id.lblBack);

        menu.setAdapter(adapter);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NfcActivity.class);
                start(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                start(i);
            }
        });
    }

    /**
     * Passer à l'activité passé en paramètre
     * @param i Motif de l'activité suivante
     */
    void start(Intent i) {
        i.putExtra("command", command);
        startActivity(i);
        finish();
    }
}
