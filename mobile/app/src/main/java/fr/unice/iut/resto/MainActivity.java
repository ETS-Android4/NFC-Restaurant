package fr.unice.iut.resto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connect = (Button) findViewById(R.id.btnConnection);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText login = (EditText) findViewById(R.id.txtLogin);
                EditText password = (EditText) findViewById(R.id.txtPassword);

                if (login.getText().toString().isEmpty()) {
                    login.setError(getResources().getString(R.string.errLogin));
                    return;
                }

                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.errPassword));
                    return;
                }

                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
    }
}
