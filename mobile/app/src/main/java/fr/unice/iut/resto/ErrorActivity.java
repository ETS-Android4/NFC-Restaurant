package fr.unice.iut.resto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ErrorActivity extends AppCompatActivity {

    String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        error = getIntent().getExtras().getString("error");

        TextView errCode = (TextView) findViewById(R.id.lblErrorCode);
        TextView errText = (TextView) findViewById(R.id.lblErrorText);

        if (error.length()<11)
            errCode.setText(error);
        else
            errText.setText(error);

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
