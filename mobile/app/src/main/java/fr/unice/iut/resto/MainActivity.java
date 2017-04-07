package fr.unice.iut.resto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connect = (Button) findViewById(R.id.btnConnection);
        login = (EditText) findViewById(R.id.txtLogin);
        password = (EditText) findViewById(R.id.txtPassword);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                if (login.getText().toString().isEmpty()) {
                    login.setError(getResources().getString(R.string.errLogin));
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.errPassword));
                    return;
                }
                send();
                */
                ArrayList<Food> command = new ArrayList<>();
                User user = new User("00", "00");
                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                i.putExtra("command", command);
                i.putExtra("user", user);
                startActivity(i);
                finish();
            }
        });
    }

    public void send() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Requests send = retrofit.create(Requests.class);
        Call<String> call = send.sendUser(login.getText().toString(), password.getText().toString());

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code()>199 && response.code()<206) {
                    ArrayList<Food> command = new ArrayList<>();
                    User user = new User(login.getText().toString(), response.body());
                    Intent i = new Intent(MainActivity.this, MenuActivity.class);
                    i.putExtra("command", command);
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
