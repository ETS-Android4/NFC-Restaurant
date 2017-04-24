package fr.unice.iut.resto;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NfcActivity extends AppCompatActivity {

    private static final String DATA = "A-01";
    private static final int PAGE = 4;
    NfcAdapter nfc;
    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        user = new User(this);
        user.checkSession();

        command = getIntent().getExtras().getParcelableArrayList("command");

        nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc == null) {
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
        nfc.enableForegroundDispatch(this, pendInt, null, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        nfc.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            write(tag, DATA, PAGE);
            send(read(tag));
        }
    }

    void write(Tag tag, String data, int page) {
        MifareUltralight ultra = null;
        try {
            ultra = MifareUltralight.get(tag);
            ultra.connect();
            ultra.writePage(page, data.getBytes("US-ASCII"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (ultra != null) {
                try {
                    ultra.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    String read(Tag tag) {
        MifareUltralight ultra = MifareUltralight.get(tag);
        try {
            ultra.connect();
            return new String(ultra.readPages(4), Charset.forName("US-ASCII"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (ultra != null) {
                try {
                    ultra.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    void send(String table) {
        JSONObject order = new JSONObject();
        try {
            order.put("U_idUsers", user.getLogin());
            order.put("T_idTables", table);
            order.put("Horodatage", Calendar.getInstance().getTime());
            order.put("details", command);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Requests send = retrofit.create(Requests.class);
        Call<Void> call = send.sendCommand(order);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    i.putExtra("command", new ArrayList<>());
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
