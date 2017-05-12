package fr.unice.iut.resto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe pour s'inscrire à l'application
 * @author ER
 * @version 1.0
 */
public class SignActivity extends AppCompatActivity {

    private static final String TAG = "Sign Activity";
    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText password;
    ProgressDialog load;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        user = new User(this);
        user.isLogged();

        load = new ProgressDialog(this);
        load.setCancelable(false);
        load.setMessage("Loading...");

        firstName = (EditText) findViewById(R.id.txtFirstName);
        lastName = (EditText) findViewById(R.id.txtLastName);
        phone = (EditText) findViewById(R.id.txtPhone);
        password = (EditText) findViewById(R.id.txtPassword);
        Button sign = (Button) findViewById(R.id.btnSign);
        TextView back = (TextView) findViewById(R.id.lblBack);

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
                send(firstName.getText().toString(), lastName.getText().toString(),
                        phone.getText().toString(), password.getText().toString());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    /**
     * Envoyer une requête HTTP au serveur pour inscrire un utilisateur
     * @param n1 Prénom de l'utilisateur
     * @param n2 Nom de l'utilisateur
     * @param ph Téléphone de l'utilisateur
     * @param pw Mot de passe de l'utilisateur
     */
    void send(final String n1, final String n2, final String ph, final String pw) {
        load.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Requests send = retrofit.create(Requests.class);
        Call<Void> call = send.sendData(n1, n2, ph, pw);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                load.dismiss();
                if (response.code() == 201) {
                    user.createSession(ph);
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error " + response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                load.dismiss();
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.errConnection), Toast.LENGTH_LONG).show();
            }
        });
    }
}
