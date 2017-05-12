package fr.unice.iut.resto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
 * Classe pour se connecter à l'application
 * @author ER
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login Activity";
    EditText phone;
    EditText password;
    ProgressDialog load;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = new User(this);
        user.isLogged();

        load = new ProgressDialog(this);
        load.setCancelable(false);
        load.setMessage("Loading...");

        phone = (EditText) findViewById(R.id.txtPhone);
        password = (EditText) findViewById(R.id.txtPassword);
        Button login = (Button) findViewById(R.id.btnLogin);
        TextView sign = (TextView) findViewById(R.id.lblSign);

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
                send(phone.getText().toString(), password.getText().toString());
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignActivity.class));
                finish();
            }
        });
    }

    /**
     * Envoyer une requête HTTP au serveur pour connecter un utilisateur
     * @param ph Téléphone de l'utilisateur
     * @param pw Mot de passe de l'utilisateur
     */
    void send(final String ph, final String pw) {
        load.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Requests send = retrofit.create(Requests.class);
        Call<Void> call = send.sendUser(ph, pw);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                load.dismiss();
                if (response.code() == 200) {
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
