package fr.unice.iut.resto;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe pour envoyer la commande
 * @author ER
 * @version 1.0
 */
public class NfcActivity extends AppCompatActivity {

    private static final String TAG = "Nfc Activity";
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    ArrayList<Food> command;
    NfcAdapter nfcAdapter;
    ProgressDialog load;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        user = new User(this);
        user.checkSession();

        command = getIntent().getExtras().getParcelableArrayList("command");

        load = new ProgressDialog(this);
        load.setCancelable(false);
        load.setMessage("Loading...");

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.errNfc), Toast.LENGTH_LONG).show();
            finish();
        }

        TextView back = (TextView) findViewById(R.id.lblBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OrderActivity.class);
                i.putExtra("command", command);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        PendingIntent pendInt = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        nfcAdapter.enableForegroundDispatch(this, pendInt, null, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            send(hex(tag.getId()));
        }
    }

    /**
     * Envoyer une requête HTTP au serveur pour passer la commande de l'utilisateur
     * @param guid GUID d'un TAG
     */
    void send(String guid) {
        load.show();
        ArrayList<String> object = new ArrayList<>();
        JSONObject order = new JSONObject();
        for (Food food : command) object.add(food.getName());
        try {
            order.put("U_idUsers", user.getLogin());
            order.put("T_idTables", guid);
            order.put("Horodatage", String.valueOf(Calendar.getInstance().getTime()));
            order.put("details", object);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Requests send = retrofit.create(Requests.class);
        Call<Void> call = send.sendCommand(order.toString());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                load.dismiss();
                if (response.code() == 201) {
                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    i.putExtra("command", new ArrayList<>());
                    startActivity(i);
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

    /**
     * Convertir un tableau de byte en hexadecimal
     * @param bytes Tableau de byte à convertir
     * @return un hexadecimal sous forme d'une chaîne de caractère
     */
    String hex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j=0; j<bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
