package org.idnp.activityforresultsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private static final int RESULT_CODE_INSERT = 200;
    private static final int RESULT_CODE_UPDATE = 201;
    public static final String MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnClose = findViewById(R.id.button);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MESSAGE, "Desde el segundo activity");
                setResult(RESULT_CODE_INSERT, intent);
                finish();//finishing activity
            }
        });

    }

}