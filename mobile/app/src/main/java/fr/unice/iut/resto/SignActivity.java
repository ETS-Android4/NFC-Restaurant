package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignActivity extends AppCompatActivity {

    private static final String TAG = "SignActivity";
    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        Button sign = (Button) findViewById(R.id.btnSign);
        TextView back = (TextView) findViewById(R.id.lblBack);
        firstName = (EditText) findViewById(R.id.txtFirstName);
        lastName = (EditText) findViewById(R.id.txtLastName);
        phone = (EditText) findViewById(R.id.txtPhone);
        password = (EditText) findViewById(R.id.txtPassword);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstName.getText().toString().isEmpty()) {
                    firstName.setError(getResources().getString(R.string.errFirstName));
                    return;
                }
                if (lastName.getText().toString().isEmpty()) {
                    lastName.setError(getResources().getString(R.string.errLastName));
                    return;
                }
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void send() {

        String name1 = firstName.getText().toString();
        String name2 = lastName.getText().toString();
        String number = phone.getText().toString();
        String pass = password.getText().toString();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Requests send = retrofit.create(Requests.class);
        Call<String> call = send.sendData(name1, name2, number, pass);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code()>199 && response.code()<206) {
                    ArrayList<Food> command = new ArrayList<>();
                    User user = new User(phone.getText().toString(), response.body());
                    Intent i = new Intent(SignActivity.this, MenuActivity.class);
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
