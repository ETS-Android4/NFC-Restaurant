package fr.unice.iut.resto;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NfcActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    ArrayList<Food> command;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        user = new User(this);
        user.checkSession();

        command = getIntent().getExtras().getParcelableArrayList("command");

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
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction()) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction()) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] id = tag.getId();
            String[] tech = tag.getTechList();
            int content = tag.describeContents();
            Ndef ndef = Ndef.get(tag);
            boolean isWritable = ndef.isWritable();
            boolean canMakeReadOnly = ndef.canMakeReadOnly();
            Parcelable[] rawMsg = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msg;
            String message = null;
            if (rawMsg != null) {
                msg = new NdefMessage[rawMsg.length];
                for (int i = 0; i < rawMsg.length; i++) {
                    msg[i] = (NdefMessage) rawMsg[i];
                    NdefRecord record = msg[i].getRecords()[i];
                    byte[] idRec = record.getId();
                    short tnf = record.getTnf();
                    byte[] type = record.getType();
                    message = getTextData(record.getPayload());
                }
            }
            send(message);
        }
    }

    String getTextData(byte[] payload) {
        String textCode = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
        int langSize = payload[0] & 0077;
        try {
            return new String(payload, langSize + 1, payload.length - langSize - 1, textCode);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
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
