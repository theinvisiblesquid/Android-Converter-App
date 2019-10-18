package ie.aa.bis.is4447.mytemperatureconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {

    private TextView tvOutput;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvOutput = findViewById(R.id.tvOutput);
        btnBack = findViewById(R.id.btnBack);

        Intent i = getIntent();

        //taking the string from the extra that was passed on the intent
        String output = String.valueOf(i.getStringExtra("convertedExtra"));

        //populate text view
        tvOutput.setText(output);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
