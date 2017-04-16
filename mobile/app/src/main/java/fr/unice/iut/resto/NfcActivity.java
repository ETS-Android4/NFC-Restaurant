package fr.unice.iut.resto;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NfcActivity extends AppCompatActivity {

    private static final String TAG = "NfcActivity";
    private static final String DATA = "A-01";
    private static final int PAGE = 4;
    NfcAdapter nfc;
    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        try {
            command = getIntent().getExtras().getParcelableArrayList("command");
            user = getIntent().getExtras().getParcelable("user");
        }
        catch(Exception e) {
            Log.e(TAG, e.toString());
        }

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
                Intent i = new Intent(NfcActivity.this, OrderActivity.class);
                i.putExtra("command", command);
                i.putExtra("user", user);
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
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        finally {
            if (ultra != null) {
                try {
                    ultra.close();
                }
                catch (IOException e) {
                    Log.e(TAG, e.toString());
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
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        finally {
            if (ultra != null) {
                try {
                    ultra.close();
                }
                catch (IOException e) {
                    Log.e(TAG, e.toString());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    void send(String table) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Requests.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Requests send = retrofit.create(Requests.class);
        Call<Void> call = send.sendCommand(table, user.getLogin(), command);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code()>199 && response.code()<206) {
                    Intent i = new Intent(NfcActivity.this, MenuActivity.class);
                    i.putExtra("command", new ArrayList<>());
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
                else {
                    Log.e(TAG, String.valueOf(response.code()));
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
