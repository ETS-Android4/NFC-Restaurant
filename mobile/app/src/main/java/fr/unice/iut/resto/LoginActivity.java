package fr.unice.iut.resto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    EditText phone;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.btnLogin);
        TextView sign = (TextView) findViewById(R.id.lblSign);
        phone = (EditText) findViewById(R.id.txtPhone);
        password = (EditText) findViewById(R.id.txtPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().isEmpty()) {
                    phone.setError(getResources().getString(R.string.errPhone));
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.errPassword));
                    return;
                }
                send();
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignActivity.class));
                finish();
            }
        });
    }

    void send() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Requests send = retrofit.create(Requests.class);
        Call<String> call = send.sendUser(phone.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code()>199 && response.code()<206) {
                    ArrayList<Food> command = new ArrayList<>();
                    User user = new User(phone.getText().toString(), response.body());
                    Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                    i.putExtra("command", command);
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
                else {
                    Log.e(TAG, String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
