package fr.unice.iut.resto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connect = (Button) findViewById(R.id.btnConnection);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = (EditText) findViewById(R.id.txtLogin);
                password = (EditText) findViewById(R.id.txtPassword);
                if (login.getText().toString().isEmpty()) {
                    login.setError(getResources().getString(R.string.errLogin));
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.errPassword));
                    return;
                }
                Log.i(TAG, "Retrofit");
            }
        });
    }
}
