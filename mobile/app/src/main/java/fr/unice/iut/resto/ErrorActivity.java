package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ErrorActivity extends AppCompatActivity {

    String err;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        user = new User(ErrorActivity.this);
        user.checkSession();

        try {
            err = getIntent().getExtras().getString("error");
        }
        catch (Exception e) {
            e.printStackTrace();
            finish();
        }

        TextView errCode = (TextView) findViewById(R.id.lblErrorCode);
        TextView errText = (TextView) findViewById(R.id.lblErrorText);

        if (err.length()<11)
            errCode.setText(err);
        else
            errText.setText(err);

        Button home = (Button) findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ErrorActivity.this, MenuActivity.class));
                finish();
            }
        });
    }
}
